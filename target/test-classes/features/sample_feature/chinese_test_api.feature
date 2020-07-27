# language: zh-CN
功能: 回归测试
  开发任务完成后, 需要进行回归测试

  @chn_api_demo
  场景大纲: 确认API调用能够正常返回期待的code
  假如 Restful服务含有以下"<URI>"
  当 我用"<Param>"发起"<Method>"请求
  那么 我应该能够得到"<StatusCode>"

    例子:
      | URI         | Method | Param                   | StatusCode |
      | /easymock01 | GET    |                         | 200        |
      | /easymock02 | POST   | {\"input\":\"test\"}    | 200        |
      | /easymock02 | POST   | {\"input\":\"test111\"} | 204        |