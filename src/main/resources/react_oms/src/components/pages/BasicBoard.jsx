// Board, 根据不同的用户, 展示不同的界面

import React from 'react';
import BreadcrumbCustom from '../BreadcrumbCustom';
import MerchantOrdersTable from '../tables/MerchantOrdersTable'
import MerchantGoodsTable from '../tables/MerchantGoodsTable'
import AuthWidget from '@/components/widget/AuthWidget';
import AllGoods from '@/components/ui/AllGoods'
import UserOrdersTable from '@/components/tables/UserOrdersTable'

import { getMerchantIncome, getUserOrders, getUserExpense, getMerchantOrders, getMerchantGoods } from '../../axios'

import { Row, Col, Card, Icon } from 'antd';


class BasicBoard extends React.Component {
    state = {
        animated: '',
        income: 'loading.',
        merchantOrderNum: 'loading.',
        goodsNum: 'loading.',
        userOrderNum: 'loading.',
        expense: 'loading.'
    };
    componentDidMount() {
        this.start();
    }
    enter = () => {
        this.setState({ animated: 'hinge' });
    };
    start = () => {
        this.setState({ income: 'loading..' });
        getMerchantIncome().then(({ income }) => {
            this.setState({
                income: income
            });
        });
        getMerchantOrders().then(({ totalResults }) => {
            this.setState({
                merchantOrderNum: totalResults
            });
        });
        getMerchantGoods().then(({ totalResults }) => {
            this.setState({
                goodsNum: totalResults
            });
        });
        getUserOrders().then(({ totalResults }) => {
            this.setState({
                userOrderNum: totalResults
            });
        });
        getUserExpense().then(({ expense }) => {
            this.setState({
                expense: expense
            });
        });
    };
    render() {
        // const { auth = {} } = this.props;
        // console.log("auth", auth);
        return (
            <div className="gutter-example button-demo">
                <BreadcrumbCustom />
                {/* ============== 商家可见开始 ============== */}
                <AuthWidget
                    children={(auth) => (
                        <Row gutter={10}>
                            {
                                auth.roleType === 1 &&
                                <Col span={24}>
                                    <MerchantOrdersTable />
                                </Col>
                            }
                        </Row>
                    )}
                />
                <AuthWidget
                    children={(auth) => (
                        <Row gutter={10}>
                            {
                                auth.roleType === 1 && <Col className="gutter-row" md={8}>
                                    <div className="gutter-box">
                                        <Card bordered={false}>
                                            <div className="clear y-center">
                                                <div className="pull-left mr-m">
                                                    <Icon type="money-collect" className="text-2x text-danger" />
                                                </div>
                                                <div className="clear">
                                                    <div className="text-muted">总收入</div>
                                                    <h2>{this.state.income}</h2>
                                                </div>
                                            </div>
                                        </Card>
                                    </div>
                                </Col>
                            }
                            {auth.roleType === 1 &&
                                <Col className="gutter-row" md={8}>
                                    <div className="gutter-box">
                                        <Card bordered={false}>
                                            <div className="clear y-center">
                                                <div className="pull-left mr-m">
                                                    <Icon type="tablet" className="text-2x text-danger" />
                                                </div>
                                                <div className="clear">
                                                    <div className="text-muted">订单数量</div>
                                                    <h2>{this.state.merchantOrderNum}</h2>
                                                </div>
                                            </div>
                                        </Card>
                                    </div>
                                </Col>}
                            {auth.roleType === 1 &&
                                <Col className="gutter-row" md={8}>
                                    <div className="gutter-box">
                                        <Card bordered={false}>
                                            <div className="clear y-center">
                                                <div className="pull-left mr-m">
                                                    <Icon type="gift" className="text-2x text-danger" />
                                                </div>
                                                <div className="clear">
                                                    <div className="text-muted">商品数量</div>
                                                    <h2>{this.state.goodsNum}</h2>
                                                </div>
                                            </div>
                                        </Card>
                                    </div>
                                </Col>
                            }
                        </Row>
                    )}
                />
                <AuthWidget
                    children={(auth) => (
                        <Row gutter={10}>
                            {
                                auth.roleType === 1 &&
                                <Col span={24}>
                                    <MerchantGoodsTable />
                                </Col>
                            }
                        </Row>
                    )}
                />
                {/* ============== 商家可见结束 ============== */}
                {/* ============== 用户以及未登录可见开始 ============== */}
                <AuthWidget
                    children={(auth) => (
                        <Row gutter={10}>
                            {
                                auth.roleType === 2 &&
                                <Col span={24}>
                                    <UserOrdersTable />
                                </Col>
                            }
                        </Row>
                    )}
                />
                <AuthWidget
                    children={(auth) => (
                        <Row gutter={10}>
                            {
                                auth.roleType === 2 && <Col className="gutter-row" md={12}>
                                    <div className="gutter-box">
                                        <Card bordered={false}>
                                            <div className="clear y-center">
                                                <div className="pull-left mr-m">
                                                    <Icon type="money-collect" className="text-2x text-danger" />
                                                </div>
                                                <div className="clear">
                                                    <div className="text-muted">总支出</div>
                                                    <h2>{this.state.expense}</h2>
                                                </div>
                                            </div>
                                        </Card>
                                    </div>
                                </Col>
                            }
                            {auth.roleType === 2 &&
                                <Col className="gutter-row" md={12}>
                                    <div className="gutter-box">
                                        <Card bordered={false}>
                                            <div className="clear y-center">
                                                <div className="pull-left mr-m">
                                                    <Icon type="tablet" className="text-2x text-danger" />
                                                </div>
                                                <div className="clear">
                                                    <div className="text-muted">订单数量</div>
                                                    <h2>{this.state.userOrderNum}</h2>
                                                </div>
                                            </div>
                                        </Card>
                                    </div>
                                </Col>
                            }
                        </Row>
                    )}
                />
                <AuthWidget
                    children={(auth) => (
                        <Row gutter={10}>
                            {
                                (auth.roleType === undefined || auth.roleType === 2) &&
                                <Col span={24}>
                                <AllGoods />
                                </Col>
                            }
                        </Row>
                    )}
                />
                {/* ============== 用户以及未登录可见结束, 商户不需要浏览所有商品 ============== */}
            </div>
        )
    }
}

export default BasicBoard;