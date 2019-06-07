// 路由跳转规则
// App.js中, 向此处的路由组件传递了auth参数(用户权限)
// 在路由组件中判断用户权限, 选择是否应该渲染组件

import React, { Component } from 'react';
import { Route, Redirect, Switch } from 'react-router-dom';
import DocumentTitle from 'react-document-title';
import AllComponents from '../components';
import routesConfig from './config';
import queryString from 'query-string';

export default class CRouter extends Component {
    requireAuth = (permission, component) => {
        const { auth } = this.props;
        const { permissions } = auth.data;
        // const { auth } = store.getState().httpData;
        // 无路由权限列表, 或路由权限列表中不包括请求的路由: 则跳转到404!
        if (!permissions || !permissions.includes(permission)) return <Redirect to={'404'} />;
        return component;
    };
    // 开发环境中, 为了方便调试, 免去登录界面的路由跳转, 即使没有权限, 也会执行最后一个return: 执行component的渲染
    requireLogin = (component, permission) => {
        const { auth } = this.props;
        const { permissions } = auth.data;
        // 路由权限列表为空, 跳转到登录
        if (process.env.NODE_ENV === 'production' && !permissions) { // 线上环境判断是否登录
            return <Redirect to={'/login'} />;
        }
        // if (process.env.NODE_ENV === 'development' && !permissions) { // 开发环境判断是否登录
        //     return <Redirect to={'/login'} />;
        // }
        // 路由权限列表不为空, 若当前请求路由为空?(渲染组件):(属于权限列表?渲染组件:404)
        return permission ? this.requireAuth(permission, component) : component;
    };
    render() {
        return (
            <Switch>
                {
                    Object.keys(routesConfig).map(key =>
                        routesConfig[key].map(r => {
                            const route = r => {
                                const Component = AllComponents[r.component];
                                return (
                                    <Route
                                        key={r.route || r.key}
                                        exact
                                        path={r.route || r.key}
                                        render={props => {
                                            const reg = /\?\S*/g;
                                            // 匹配?及其以后字符串
                                            const queryParams = window.location.hash.match(reg);
                                            // 去除?的参数
                                            const { params } = props.match;
                                            Object.keys(params).forEach(key => {
                                                params[key] = params[key] && params[key].replace(reg, '');
                                            });
                                            props.match.params = { ...params };
                                            const merge = { ...props, query: queryParams ? queryString.parse(queryParams[0]) : {} };
                                            // 重新包装组件
                                            const wrappedComponent = (
                                                <DocumentTitle title={r.title}>
                                                    <Component {...merge} />
                                                </DocumentTitle>
                                            )
                                            // 在/routes/config.js中指定"auth"字段的组件才会判断权限, 否则默认渲染
                                            return r.login
                                                ? wrappedComponent
                                                : this.requireLogin(wrappedComponent, r.auth)
                                        }}
                                    />
                                )
                            }
                            return r.component ? route(r) : r.subs.map(r => route(r));
                        })
                    )
                }

                <Route render={() => <Redirect to="/404" />} />
            </Switch>
        )
    }
}