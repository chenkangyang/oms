/**
 * 访问接口
 */
import axios from 'axios';
import { get, post } from './tools';
import * as config from './config';

export const getBbcNews = () => get({ url: config.NEWS_BBC });

export const npmDependencies = () => axios.get('./npm.json').then(res => res.data).catch(err => console.log(err));

export const weibo = () => axios.get('./weibo.json').then(res => res.data).catch(err => console.log(err));

export const gitOauthLogin = () => get({ url: `${config.GIT_OAUTH}/authorize?client_id=792cdcd244e98dcd2dee&redirect_uri=http://localhost:3006/&scope=user&state=reactAdmin` });
export const gitOauthToken = code => post({
    url: `https://cors-anywhere.herokuapp.com/${config.GIT_OAUTH}/access_token`,
    data: {
        client_id: '792cdcd244e98dcd2dee',
        client_secret: '81c4ff9df390d482b7c8b214a55cf24bf1f53059',
        redirect_uri: 'http://localhost:3006/',
        state: 'reactAdmin',
        code,
    }
});
// {headers: {Accept: 'application/json'}}
export const gitOauthInfo = access_token => get({ url: `${config.GIT_USER}access_token=${access_token}` });

// easy-mock数据交互
// 管理员权限获取
export const admin = () => get({ url: config.MOCK_AUTH_ADMIN });
// 访问权限获取
export const guest = () => get({ url: config.MOCK_AUTH_VISITOR });

// 获取商家订单
export const getMerchantOrders = () => post({ url: config.MOCK_AUTH_ADMIN_ORDER });

// 商家查询收入
export const getMerchantIncome = () => post({ url: config.MOCK_AUTH_ADMIN_INCOME });

// 商家查询所有商品
export const getMerchantGoods = () => post({ url: config.MOCK_AUTH_ADMIN_GOODS });

// 商家发货
export const setDelivery = (data) => post({ url: config.MOCK_AUTH_ADMIN_DELIVERY, data: data});

// 商家上架新商品
export const newMerchantGoods = (data) => post({ url: config.MOCK_AUTH_ADMIN_NEW_GOODS, data: data});

// 商家删除一组商品
export const deleteMerchantGoods = (data) => post({ url: config.MOCK_AUTH_ADMIN_DEL_GOODS, data: data});

// 用户提交一个订单
export const newUserOrder = (data) => post({ url: config.MOCK_AUTH_USER_NEW_ORDER, data: data});

// 用户查询所有订单
export const getUserOrders = () => post({ url: config.MOCK_AUTH_USER_GET_ORDERS});

// 用户查询所有支出
export const getUserExpense = () => post({ url: config.MOCK_AUTH_USER_EXPENSE});

// 用户查询所有商品
export const getUserGoods = () => post({ url: config.MOCK_AUTH_USER_GET_GOODS});

// 登录
export const login = (data) => post({ url: config.MOCK_AUTH_LOGIN, data: data});

// 退货? 不存在的 - -

