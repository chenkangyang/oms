/**
 * 路由组件出口文件
 */
import MerchantOrdersTable from './tables/MerchantOrdersTable';
import MerchantGoodsTable from './tables/MerchantGoodsTable';
import UserOrdersTable from './tables/UserOrdersTable';

import AllGoods from './ui/AllGoods'
import AuthBasic from './auth/Basic';
import RouterEnter from './auth/RouterEnter';
import QueryParams from './extension/QueryParams';
import BasicBoard from './pages/BasicBoard'

export default {
    AllGoods, AuthBasic, RouterEnter, QueryParams, BasicBoard, 
    MerchantOrdersTable, MerchantGoodsTable, UserOrdersTable
}