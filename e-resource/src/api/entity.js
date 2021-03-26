import http from '@/utils/http'

let baseURL = '/v1.0'

/**
 * 根据关键词查找对应的知识点
 * - keyword: 查找的关键词
 * - page: 第几页
 * - perPage: 每页多少个
 * @param {Object} params
 * @param {String} params.keyword
 * @param {String} params.page
 * @param {String} params.perPage
 * @returns {AxiosPromise}
 */
export const searchEntity = (params) => {
  let data = ''
  for (let info in params) {
    data += info + '=' + params[info] + '&'
  }
  console.log(data)
  return http.get(`${baseURL}/public/queryEntity?${data}`)
}

/**
 * 根据关键词查询相关联的知识点
 * - keyword: 查找的关键词
 * @param {string} keyword
 * @returns {AxiosPromise}
 */
export const relatedEntity = (keyword) => {
  // 用%23替换#，否则后端无法接收
  keyword = keyword.replaceAll('#', '%23')
  return http.get(`${baseURL}/public/relatedEntity?keyword=${keyword}`)
}
