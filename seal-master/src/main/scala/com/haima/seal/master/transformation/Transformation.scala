/**
  * @author: Huawei niu
  * @version:
  * @createDate ：2018/8/6
  * @desc:
  */
package com.haima.seal.master.transformation


//#messages
final case class TransformationJob(text: String)
final case class TransformationResult(text: String)
final case class JobFailed(reason: String, job: TransformationJob)
case object BackendRegistration
//#messages

