/**
 * 接口地址配置文件
 */

//easy-mock模拟数据接口地址
const EASY_MOCK = 'https://www.easy-mock.com/mock';
// const MOCK_AUTH = EASY_MOCK + '/597b5ed9a1d30433d8411456/auth'; // 权限接口地址
const MOCK_AUTH = EASY_MOCK + '/5cf60a9a24fe244fb04f720d/auth'; // 权限接口地址
export const MOCK_AUTH_ADMIN = MOCK_AUTH + '/admin'; // 管理员权限接口
export const MOCK_AUTH_VISITOR = MOCK_AUTH + '/visitor' // 访客权限接口
export const MOCK_AUTH_ADMIN_ORDER = MOCK_AUTH_ADMIN + '/getorders' // 商家订单接口
export const MOCK_AUTH_ADMIN_INCOME = MOCK_AUTH_ADMIN + '/getincome' // 商家查询收入接口    
export const MOCK_AUTH_ADMIN_DELIVERY = MOCK_AUTH_ADMIN + '/delivery' // 商家发货接口
export const MOCK_AUTH_ADMIN_GOODS = MOCK_AUTH_ADMIN + '/getgoods' // 商家货物接口
export const MOCK_AUTH_ADMIN_NEW_GOODS = MOCK_AUTH_ADMIN + '/newgoods' // 商家上架接口
export const MOCK_AUTH_ADMIN_DEL_GOODS = MOCK_AUTH_ADMIN + '/delgoods' // 商家下架接口
export const MOCK_AUTH_USER_NEW_ORDER = MOCK_AUTH_VISITOR + '/neworder' // 用户提交订单接口
export const MOCK_AUTH_USER_GET_ORDERS = MOCK_AUTH_VISITOR + '/getorders' // 用户查询所有订单接口
export const MOCK_AUTH_USER_GET_GOODS = MOCK_AUTH_VISITOR + '/getgoods' // 用户查询所有商品接口
export const MOCK_AUTH_USER_EXPENSE = MOCK_AUTH_VISITOR + '/getexpense' // 用户查询支出接口
export const MOCK_AUTH_LOGIN = MOCK_AUTH + '/login'; // 登录接口


// github授权
export const GIT_OAUTH = 'https://github.com/login/oauth';
// github用户
export const GIT_USER = 'https://api.github.com/user';

// bbc top news
export const NEWS_BBC = 'https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=429904aa01f54a39a278a406acf50070';




