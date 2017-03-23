package com.skypiea.common.http;

/**
 * 作者: huangwenjian
 * 描述: 响应码
 * 创建时间: 2017-03-19 16:44
 */
public interface HttpStatus {

    //指示客户端可能继续其请求
    int Continue = 100;

    //指示正在更改协议版本或协议
    int SwitchingProtocols = 101;

    //指示请求成功，且请求的信息包含在响应中。 这是最常接收的状态代码
    int OK = 200;

    //指示请求导致在响应被发送前创建新资源
    int Created = 201;

    //指示请求已被接受做进一步处理
    int Accepted = 202;

    //指示返回的元信息来自缓存副本而不是原始服务器，因此可能不正确
    int NonAuthoritativeInformation = 203;

    //指示已成功处理请求并且响应已被设定为无内容
    int NoContent = 204;

    //指示客户端应重置（或重新加载）当前资源
    int ResetContent = 205;

    //指示响应是包括字节范围的GET请求所请求的部分响应
    int PartialContent = 206;

    //指示请求的信息有多种表示形式,默认操作是将此状态视为重定向，并遵循与此响应关联的Location标头的内容
    int MultipleChoices = 300;

    //指示请求的信息已移到Location头中指定的URI处,接收到此状态时的默认操作为遵循与响应关联的Location头
    int MovedPermanently = 301;

    //重定向
    int Redirect = 302;

    //对应当前请求的响应可以在另一个URI上被找到，而且客户端应当采用GET的方式访问那个资源
    int SeeOther = 303;

    //指示客户端的缓存副本是最新的。 未传输此资源的内容。
    int NotModified = 304;

    //被请求的资源必须通过指定的代理才能被访问
    int UserProxy = 305;

    //请求的资源现在临时从不同的URI响应请求
    int TemporaryRedirect = 307;

    //指示服务器未能识别请求
    int BadRequest = 400;

    //指示请求的资源要求身份验证
    int Unauthorized = 401;

    //该状态码是为了将来可能的需求而预留的
    int PaymentRequired = 402;

    //服务器已经理解请求，但是拒绝执行它
    int Forbidden = 403;

    //请求失败，请求所希望得到的资源未被在服务器上发现
    int NotFound = 404;

    //请求行中指定的请求方法不能被用于请求相应的资源
    int MethodNotAllowed = 405;

    //请求的资源的内容特性无法满足请求头中的条件，因而无法生成响应实体
    int NotAcceptable = 406;

    //指示请求的代理要求身份验证
    int ProxyAuthenticationRequired = 407;

    //指示客户端没有在服务器期望请求的时间内发送请求
    int RequestTimeout = 408;

    //指示由于服务器上的冲突而未能执行请求
    int Conflict = 409;

    //指示请求的资源不再可用
    int Gone = 410;

    //指示缺少必需的 Content-length头
    int LengthRequired = 411;

    //指示无法返回从资源请求的数据范围
    int RequestedRangeNotSatisfiable = 416;

    //指示服务器上发生了一般错误
    int InternalServerError = 500;

    //指示服务器不支持请求的函数
    int NotImplemented = 501;

    //指示中间代理服务器从另一代理或原始服务器接收到错误响应
    int BadGateway = 502;

    //示服务器暂时不可用，通常是由于过多加载或维护
    int ServiceUnavailable = 503;

    //指示中间代理服务器在等待来自另一个代理或原始服务器的响应时已超时
    int GatewayTimeout = 504;

    //指示服务器不支持请求的HTTP 版本
    int HttpVersionNotSupported = 505;
}
