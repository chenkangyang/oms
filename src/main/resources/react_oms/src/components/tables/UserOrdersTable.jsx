import React from 'react';
import { Table, Button, Row, Col, Card } from 'antd';
import { getUserOrders } from '../../axios';


const columns = [{
    title: '订单ID',
    dataIndex: 'order_id',
    width: 80,
}, {
    title: '商户ID',
    dataIndex: 'seller_id',
    width: 80,
}, {
    title: '商户名称',
    dataIndex: 'seller_name',
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

class UserOrdersTable extends React.Component {
    state = {
        selectedRowKeys: [], // Check here to configure the default column
        loading: false,
        data: []
    };
    componentDidMount() {
        this.fresh();
    }
    fresh = () => {
        this.setState({ loading: true });
        getUserOrders().then(({ orders }) => {
            this.setState({
                data: orders,
                loading: false
            });
        });
    };

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
                            <Card title="现有订单" bordered={false}>
                                <div style={{ marginBottom: 16 }}>
                                    <Button type="primary" onClick={this.fresh}
                                        disabled={loading} loading={loading}
                                    >刷新</Button>
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

export default UserOrdersTable;