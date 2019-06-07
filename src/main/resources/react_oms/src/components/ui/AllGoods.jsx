/* eslint-disable jsx-a11y/anchor-is-valid */
import React from 'react';
import { Drawer, Divider, Row, Col, Card, Form, InputNumber, Button, message } from 'antd';
import AuthWidget from '@/components/widget/AuthWidget';

import { getUserGoods } from '../../axios';
import { newUserOrder } from '../../axios';

const { Meta } = Card;

const UserNewOrderForm = Form.create()(
    (props) => {
        const { loading, form, submitFun } = props;
        const { getFieldDecorator } = form;
        return (
            <Form layout="inline" onSubmit={submitFun}>
                <Form.Item>
                    {getFieldDecorator('order_num', {
                        rules: [{ required: true, message: '请输入订购数量!' }],
                    })(
                        <InputNumber min={0} max={99999} />
                    )}
                </Form.Item>
                <Form.Item>
                    <Button type="primary" htmlType="submit" disabled={loading} loading={loading}>
                        下单
                            </Button>
                </Form.Item>
            </Form>
        );
    }
);


const pStyle = {
    fontSize: 16,
    color: 'rgba(0,0,0,0.85)',
    lineHeight: '24px',
    display: 'block',
    marginBottom: 16,
};

const DescriptionItem = ({ title, content }) => (
    <div
        style={{
            fontSize: 14,
            lineHeight: '22px',
            marginBottom: 7,
            color: 'rgba(0,0,0,0.65)',
        }}
    >
        <p
            style={{
                marginRight: 8,
                display: 'inline-block',
                color: 'rgba(0,0,0,0.85)',
            }}
        >
            {title}:
      </p>
        {content}
    </div>
);


class AllGoods extends React.Component {
    state = {
        goods: [],
        visible: false,
        drawerData: {},
        loading: false
    };
    componentDidMount() {
        this.loadGoods();
    };
    loadGoods = () => {
        getUserGoods().then(({ data }) => {
            this.setState({
                goods: data.goods
            });
        });

    };

    showDrawer = (index) => {
        this.setState({
            visible: true,
            drawerData: this.state.goods[index]
        });
    };

    onClose = () => {
        this.setState({
            visible: false
        });
    };

    saveFormRef = (form) => {
        this.form = form;
    };

    handleSubmit = () => {
        const form = this.form;
        form.validateFields((err, values) => {
            if (err) {
                return;
            }
            values.goods_id = this.state.drawerData.goods_id;
            console.log('Received values of form: ', values);

            // 请求上架接口
            newUserOrder(values).then(({ code }) => {
                if (code === 200) {
                    message.success("下单成功");
                } else if (code === 500) {
                    message.error("下单失败");
                }
                this.setState({
                    visible: false,
                    loading: false
                });
                form.resetFields();
            });
        });
    }
    render() {
        // const { getFieldDecorator } = this.props.form;
        const goodsElements = [];
        const goodsExtra = [];
        const goodsList = this.state.goods;
        // const loading = this.state.loading;
        const col_num = 4;
        for (let i = 0; i < goodsList.length; i++) {
            if (i && !(i % col_num)) {
                goodsElements.push(
                    <Row key={i} gutter={10}>
                        <Col className="gutter-row" md={6}>
                            <Card
                                hoverable
                                style={{ width: 240 }}
                                cover={<img alt="卖家未上图哦" src={goodsList[i - col_num].goods_src} />}
                                actions={[<a onClick={(index) => this.showDrawer(i - col_num)}>View Detail</a>]}
                            >
                                <Meta title={goodsList[i - col_num].goods_name} description={goodsList[i - col_num].goods_price} />
                            </Card>
                        </Col>
                        <Col className="gutter-row" md={6}>
                            <Card
                                hoverable
                                style={{ width: 240 }}
                                cover={<img alt="卖家未上图哦" src={goodsList[i - col_num + 1].goods_src} />}
                                actions={[<a onClick={(index) => this.showDrawer(i - col_num + 1)}>View Detail</a>]}
                                curgoods={goodsList[i - col_num + 1]}
                            >
                                <Meta title={goodsList[i - col_num + 1].goods_name} description={goodsList[i - col_num + 1].goods_price} />
                            </Card>
                        </Col>
                        <Col className="gutter-row" md={6}>
                            <Card
                                hoverable
                                style={{ width: 240 }}
                                cover={<img alt="卖家未上图哦" src={goodsList[i - col_num + 2].goods_src} />}
                                actions={[<a onClick={(index) => this.showDrawer(i - col_num + 2)}>View Detail</a>]}
                                curgoods={goodsList[i - col_num + 2]}
                            >
                                <Meta title={goodsList[i - col_num + 2].goods_name} description={goodsList[i - col_num + 2].goods_price} />
                            </Card>
                        </Col>
                        <Col className="gutter-row" md={6}>
                            <Card
                                hoverable
                                style={{ width: 240 }}
                                cover={<img alt="卖家未上图哦" src={goodsList[i - col_num + 3].goods_src} />}
                                actions={[<a onClick={(index) => this.showDrawer(i - col_num + 3)}>View Detail</a>]}
                                curgoods={goodsList[i - col_num + 3]}
                            >
                                <Meta title={goodsList[i - col_num + 3].goods_name} description={goodsList[i - col_num + 3].goods_price} />
                            </Card>
                        </Col>
                    </Row>
                );
            }
            if (i >= goodsList.length - goodsList.length % col_num) {
                goodsExtra.push(
                    <Col key={i} className="gutter-row" md={6}>
                        <Card
                            hoverable
                            style={{ width: 240 }}
                            cover={<img alt="卖家未上图哦" src={goodsList[i].goods_src} />}
                            actions={[<a onClick={(index) => this.showDrawer(i)}>View Detail</a>]}
                            curgoods={goodsList[i]}
                        >
                            <Meta title={goodsList[i].goods_name} description={goodsList[i].goods_price} />
                        </Card>
                    </Col>
                );
            }
        }

        return (
            <div>
                {goodsElements}
                <Row gutter={10}>
                    {goodsExtra}
                </Row>
                <Drawer
                    width={640}
                    placement="right"
                    closable={false}
                    onClose={this.onClose}
                    visible={this.state.visible}
                >
                    <p style={{ ...pStyle, marginBottom: 24 }}>商品详情</p>
                    <Row>
                        <Col span={12}>
                            <DescriptionItem title="商品名称" content={this.state.drawerData.goods_name} />{' '}
                        </Col>
                        <Col span={12}>
                            <DescriptionItem title="商品价格" content={this.state.drawerData.goods_price} />
                        </Col>
                    </Row>
                    <Row>
                        <Col span={24}>
                            <DescriptionItem
                                title="商品详情"
                                content={this.state.drawerData.goods_description}
                            />
                        </Col>
                    </Row>
                    <Divider />
                    <p style={pStyle}>卖家信息</p>
                    <Row>
                        <Col span={12}>
                            <DescriptionItem title="买家名称" content={this.state.drawerData.seller_name} />
                        </Col>
                        <Col span={12}>
                            <DescriptionItem title="联系方式" content={this.state.drawerData.seller_contact} />
                        </Col>
                    </Row>
                    <Divider />
                    <AuthWidget
                        children={auth => (
                            <Col span={24}>
                                {auth.roleType === 2 &&
                                    <UserNewOrderForm
                                        ref={this.saveFormRef}
                                        loading={this.state.loading}
                                        submitFun={this.handleSubmit}
                                    />
                                }
                            </Col>
                        )}
                    />
                </Drawer>
            </div>
        )
    }
}

export default AllGoods;