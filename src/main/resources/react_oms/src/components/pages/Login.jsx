// 将用户信息写入localstorage

import React from 'react';
import { Form, Icon, Input, Button, Checkbox, message } from 'antd';
import { PwaInstaller } from '../widget';
import { connectAlita } from 'redux-alita';
import { login } from '../../axios'

const FormItem = Form.Item;

class Login extends React.Component {
    state = {
        user: {},
        code: null,
        errMsg: ""
    };
    componentDidMount() {
        const { setAlitaState } = this.props;
        // console.log("Login: componentDidMount");
        // console.log(this.props);
        setAlitaState({ stateName: 'auth', data: null });
    }
    componentDidUpdate(prevProps) { // React 16.3+弃用componentWillReceiveProps
        const { auth: nextAuth = {}, history } = this.props;
        // console.log("Login: componentDidUpdate");
        // console.log(this.props);
        // const { history } = this.props;
        if (nextAuth.data && nextAuth.data.uid) { // 判断是否登陆
            localStorage.setItem('user', JSON.stringify(nextAuth.data));
            history.push('/'); // 是登陆的, 则跳转到 '/'
        }
    }
    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFields((err, values) => {
            if (!err) {
                console.log('Login.jsx 提交事件调用: Received values of form: ', values);
                const { setAlitaState } = this.props;
                console.log('Login.jsx 提交事件调用: 从this.props取出setAlitaState ', setAlitaState);

                login(values).then(({code, data, errMsg}) => {
                    this.setState({
                        code: code,
                        user: data,
                        errMsg: errMsg
                    });
                    console.log("Login.jsx 请求login接口后收到回复: this.state ", this.state);

                    if (code === 200) {
                        if (data.roleType === 1) {
                            console.log("Login.jsx 判断为管理员, setAlitaState({ funcName: 'admin', stateName: 'auth' }) ");
                            setAlitaState({ funcName: 'admin', stateName: 'auth' });
                        } else if (data.roleType === 2) {
                            console.log("用户");
                            setAlitaState({ funcName: 'guest', stateName: 'auth' });
                        }
                    } else {
                        message.error(errMsg);
                    }
                });
                // if (values.userName === 'admin' && values.password === 'admin') setAlitaState({ funcName: 'admin', stateName: 'auth' });
                // if (values.userName === 'guest' && values.password === 'guest') setAlitaState({ funcName: 'guest', stateName: 'auth' });
            }
        });
    };
    gitHub = () => {
        window.location.href = 'https://github.com/login/oauth/authorize?client_id=792cdcd244e98dcd2dee&redirect_uri=http://localhost:3006/&scope=user&state=reactAdmin';
    };
    render() {
        const { getFieldDecorator } = this.props.form;
        return (
            <div className="login">
                <div className="login-form" >
                    <div className="login-logo">
                        <span>网上家具商城</span>
                        <PwaInstaller />
                    </div>
                    <Form onSubmit={this.handleSubmit} style={{maxWidth: '300px'}}>
                        <FormItem>
                            {getFieldDecorator('userName', {
                                rules: [{ required: true, message: '请输入用户名!' }],
                            })(
                                <Input prefix={<Icon type="user" style={{ fontSize: 13 }} />} placeholder="管理员输入admin, 游客输入guest" />
                            )}
                        </FormItem>
                        <FormItem>
                            {getFieldDecorator('password', {
                                rules: [{ required: true, message: '请输入密码!' }],
                            })(
                                <Input prefix={<Icon type="lock" style={{ fontSize: 13 }} />} type="password" placeholder="管理员输入admin, 游客输入guest" />
                            )}
                        </FormItem>
                        <FormItem>
                            {getFieldDecorator('remember', {
                                valuePropName: 'checked',
                                initialValue: true,
                            })(
                                <Checkbox>记住我</Checkbox>
                            )}
                            <span className="login-form-forgot" href="" style={{float: 'right'}}>忘记密码</span>
                            <Button type="primary" htmlType="submit" className="login-form-button" style={{width: '100%'}}>
                                登录
                            </Button>
                            <p style={{display: 'flex', justifyContent: 'space-between'}}>
                                <span >或 现在就去注册!</span>
                                <span onClick={this.gitHub} ><Icon type="github" />(第三方登录)</span>
                            </p>
                        </FormItem>
                    </Form>
                </div>
            </div>
        );
    }
}

export default connectAlita(['auth'])(Form.create()(Login));


/*  Redux-alita使用说明
    使用React-Redux针对此项目进行了封装
    提供4个功能:
    {AlitaProvider, connectAlita, setAlitaState, setConfig}
    AlitaProvider
        1. 使用 redux-thunk 作为异步action中间件, 生成唯一的一个容器 store, 
        2. 提供一个包裹在根组件外面的Provider组件, 使所有的子组件都能拿到 store, 它的原理是React组件的context属性
    connectAlita(state)
        1. 使用connect方法, 从 "UI组件"(App组件) 生成 "容器组件"
        2. 传入参数state, 使用mapStateToProps负责输入逻辑, 即将state映射到 "UI组件"的参数(props), 引起UI组件的更新
        3. 使用mapDispatchToProps负责输出逻辑, 即将用户对 "UI组件"的操作映射成 Action, 传给Store存储, 引起state的更新
    setAlitaState(Object)
        Object.funcName: 请求接口函数名称
        Object.stateName: category
        Object.data: data
        Object.params: 请求接口的参数
        之后 dispatch 执行一个 Action, 表示 state 要发生变化
    setConfig (apis)
        传入所有的接口, 存入funcs, 以便使用funcs[funcName](params)执行接口函数
*/