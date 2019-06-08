import React from 'react';
import { Table, Button, Row, Col, Card, message } from 'antd';
import { getMerchantOrders } from '../../axios';
import { setDelivery } from '../../axios';


const columns = [{
    title: '订单ID',
    dataIndex: 'order_id',
    width: 80,
}, {
    title: '用户ID',
    dataIndex: 'user_id',
    width: 80,
}, {
    title: '用户名称',
    dataIndex: 'user_name',
    width: 80
}, {
    title: '商品ID',
    dataIndex: 'goods_id',
    width: 80
}, {
    title: '商品名称',
    dataIndex: 'goods_name',
    width: 80
}, {
    title: '商品价格',
    dataIndex: 'goods_price',
    width: 80
}, {
    title: '订购数量',
    dataIndex: 'order_num',
    width: 80
}, {
    title: '订单总额',
    dataIndex: 'order_amount',
    width: 80
}, {
    title: '订单状态',
    dataIndex: 'order_state',
    width: 80
}];

class MerchantOrdersTable extends React.Component {
    state = {
        selectedRowKeys: [], // Check here to configure the default column
        loading: false,
        data: []
    };
    componentDidMount() {
        this.start();
    }
    start = () => {
        this.setState({ loading: true });
        getMerchantOrders().then(({ orders }) => {
            this.setState({
                data: orders,
                loading: false
            });
        });
    };

    delivery = () => { // 提交发货请求后紧接着刷新状态
        this.setState({ loading: true });

        setDelivery({
            indexs: this.state.selectedRowKeys
        }).then(({ code }) => {
            if (code === 200) {
                message.success("发货成功");
            } else if (code === 500) {
                message.error("发货失败");
            }
            this.setState({
                loading: false
            });
        });
        this.start();
    }
    onSelectChange = (selectedRowKeys) => {
        console.log('selectedRowKeys changed: ', selectedRowKeys);
        this.setState({ selectedRowKeys });
    };
    render() {
        const { loading, selectedRowKeys } = this.state;
        const rowSelection = {
            selectedRowKeys,
            onChange: this.onSelectChange,
        };
        const hasSelected = selectedRowKeys.length > 0;
        return (
            <div className="gutter-example">
                <Row gutter={16}>
                    <Col className="gutter-row" md={24}>
                        <div className="gutter-box">
                            <Card title="已接单" bordered={false}>
                                <div style={{ marginBottom: 16 }}>
                                    <Button type="primary" onClick={this.start}
                                        disabled={loading} loading={loading}
                                    >刷新</Button>
                                    <Button type="primary" onClick={this.delivery}
                                        disabled={loading} loading={loading}
                                    >发货</Button>
                                    <span style={{ marginLeft: 8 }}>{hasSelected ? `Selected ${selectedRowKeys.length} items` : ''}</span>
                                </div>
                                <Table rowSelection={rowSelection} columns={columns} dataSource={this.state.data} rowKey="order_id" />
                            </Card>
                        </div>
                    </Col>
                </Row>
            </div>
        );
    }
}

export default MerchantOrdersTable;