import React from 'react';
import { Table, Button, Row, Col, Card, Modal, Form, Input, InputNumber, message } from 'antd';
import { getMerchantGoods } from '../../axios';
import { newMerchantGoods } from '../../axios'
import { deleteMerchantGoods } from '../../axios';

const FormItem = Form.Item;


const columns = [{
    title: '商品ID',
    dataIndex: 'goods_id',
    width: 120,
}, {
    title: '商品名称',
    dataIndex: 'goods_name',
    width: 120,
}, {
    title: '商品价格',
    dataIndex: 'goods_price',
    width: 120
}, {
    title: '商品库存',
    dataIndex: 'goods_stock',
    width: 120
}];

const CollectionCreateForm = Form.create()(
    (props) => {
        const { visible, onCancel, onCreate, form } = props;
        const { getFieldDecorator } = form;
        return (
            <Modal
                visible={visible}
                title="上架新产品"
                okText="上架！"
                onCancel={onCancel}
                onOk={onCreate}
            >
                <Form layout="vertical">
                    <FormItem label="商品名称">
                        {getFieldDecorator('goods_name', {
                            rules: [{ required: true, message: '请输入商品名!' }],
                        })(
                            <Input />
                        )}
                    </FormItem>
                    <FormItem label="商品价格">
                        {getFieldDecorator('goods_price', {
                            rules: [{ required: true, message: '请输入商品价格!' }],
                        })(
                            <InputNumber
                                min={0}
                                max={99999}
                                step={0.1}
                                formatter={value => `$ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')}
                                parser={value => value.replace(/\$\s?|(,*)/g, '')}
                            />
                        )}
                    </FormItem>
                    <FormItem label="商品库存">
                        {getFieldDecorator('goods_stock', {
                            rules: [{ required: true, message: '请输入商品库存!' }],
                        })(
                            <InputNumber min={0} max={99999} />
                        )}
                    </FormItem>
                </Form>
            </Modal>
        );
    }
);



class MerchantGoodsTable extends React.Component {
    state = {
        selectedRowKeys: [], // Check here to configure the default column
        loading: false,
        visible: false,
        data: []
    };
    componentDidMount() {
        this.fresh();
    }
    fresh = () => {
        this.setState({ loading: true });
        getMerchantGoods().then(({ goods }) => {
            this.setState({
                data: goods,
                loading: false
            });
        });
    };

    deleteGoods = () => {
        this.setState({ loading: true });
        deleteMerchantGoods({
            indexs: this.state.selectedRowKeys
        }).then(({ code }) => {
            if (code === 200) {
                message.success("下架成功");
            } else if (code === 500) {
                message.error("下架失败");
            }
            this.setState({
                loading: false
            });
        });
        this.fresh();
    };

    onSelectChange = (selectedRowKeys) => {
        console.log('selectedRowKeys changed: ', selectedRowKeys);
        this.setState({ selectedRowKeys });
    };

    newGoodsModal = () => {
        this.setState({
            visible: true,
            loading: true
        });
    };
    handleCancel = () => {
        this.setState({ visible: false, loading: false });
    };
    handleCreate = () => {
        const form = this.form;
        form.validateFields((err, values) => {
            if (err) {
                return;
            }

            console.log('Received values of form: ', values);
            // 请求上架接口
            newMerchantGoods(values).then(({ code }) => {
                if (code === 200) {
                    message.success("上架成功");

                } else if (code === 500) {
                    message.error("上架失败");
                }
                this.setState({
                    visible: false,
                    loading: false
                });
                form.resetFields();
            });
        });
    };
    saveFormRef = (form) => {
        this.form = form;
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
                            <Card title="已上架产品" bordered={false}>

                                <div style={{ marginBottom: 16 }}>
                                    <Button type="primary" onClick={this.fresh}
                                        disabled={loading} loading={loading}
                                    >刷新</Button>
                                    <Button type="primary" onClick={this.newGoodsModal} disabled={loading} loading={loading}>上架</Button>
                                    <CollectionCreateForm
                                        ref={this.saveFormRef}
                                        visible={this.state.visible}
                                        onCancel={this.handleCancel}
                                        onCreate={this.handleCreate}
                                    />
                                    <Button type="primary" onClick={this.deleteGoods}
                                        disabled={loading} loading={loading}
                                    >下架</Button>
                                    <span style={{ marginLeft: 8 }}>{hasSelected ? `Selected ${selectedRowKeys.length} items` : ''}</span>
                                </div>
                                <Table rowSelection={rowSelection} columns={columns} dataSource={this.state.data} rowKey="goods_id" />
                            </Card>
                        </div>
                    </Col>
                </Row>
            </div>
        );
    }
}

export default MerchantGoodsTable;