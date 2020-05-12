package com.cloud.platform.producer.security;

public class ErrorCodeConst {
	// 注册失败
	public static final long REGFAIL = 300L;
	// 用户名或者密码错误
	public static final long USERNAMEORPASSWORDFAILURE = 301L;
	// 错误的输入
	public static final long BADREQUEST = 302L;

	// 不完整的贷款提交信息
	public static long INCOMPETEAPPLICATION = 306L;
	// 非法的银行卡信息
	public static final long ERRORCARD = 305L;
	// 非法的推荐人
	public static final long UNKNONWREFERRER = 304L;
	// 输入的密码不一致
	public static final long PWDINCONSISTENT = 307L;

	// 未知的用户名
	public static final long UNKNOWNUSERNAME = 308L;
	// 不支持的方法
	public static final long AUTHENTICATIONERROR = 309L;

	// token 信息过期
	public static final long TOKENEXPIRED = 310L;

	// 验证码不一致
	public static final long UNCOMPAREDCODE = 311L;
	// 不合法的token
	public static final long INVALIDJWT = 312L;

	// 与核心系统通讯错误
	public static final long FEIGNERROR = 313L;

	// 不合法的短信token
	public static final long SMSMALFORMEDSMSTOKEN = 314L;

	// 不一致的短信验证码
	public static final long SMSINCONSISTENTCODE = 315L;

	// 短信验证码已经过期
	public static final long SMSEXPIREDCODE = 316L;

	// 接口访问失败
	public static final long ACCESSFAILURECODE = 99L;

	// 向核心提交正式贷款申请失败
	public static final long SUBMITFORMALFAILURE = 317L;

	// 输入不能为空
	public static final long SUBMITFOREMPTY = 320L;
	// 新密码不能与旧密码相同
	public static final long DIFFERNEWANDOLD = 321L;
	// 相同的手机号码无法修改
	public static final long PHONEUNMODIFIED = 322L;
	// 身份证号码错误
	public static final long ERRORIDCARD = 323L;
	// 申请失败
	public static final long APPLYFAILURE = 324L;
	// 用户名不能为空
	public static final long NOTEMPTYUSERNAME = 325L;
	// 删除文章
	public static final long DELETEARTICLE = 326L;
	// 未知栏目类别
	public static final long UNKNOWNCATEGORY = 327L;
	// 指定的文章不存在
	public static final long UNEXISTARTICLE = 328L;
	// 不存在的标题
	public static final long UNEXISTBANNER = 329L;
	// 不能删除栏目类别，因为它仍然包含一些文章
	public static final long CANNOTDELETECATEGORY = 330L;
	// id不能为空
	public static final long UNEMPTYID = 331L;
	// 创建信息为空
	public static final long EMPTYCREATEINFO = 332L;
	// 未知产品
	public static final long UNKNOWNPRODUCT = 333L;
	// 删除
	public static final long DELETE = 334L;
	// 未知id
	public static final long UNKNOWNID = 335L;
	// 不支持的银行卡
	public static final long UNSUPPORTEDBANKCARDS = 336L;
	// 银行卡信息和身份证信息不匹配
	public static final long UNMATCHCARDINFOANDIDINFO = 337L;
	// 领取失败
	public static final long PULLFAILURE = 400L;
	// 用户信息修改失败
	public static final long MODIFYUSERINOFAILURE = 401L;

	// 查询不到最新发生改变的贷款申请消息
	public static final long  UNFOUNDLATESTAPPLICATION = 338L;

	// 三要素检测不通
	public static final long  INVALIDENTITY = 339L;

	// 创建或者修改banner失败，处于显示状态的banner已经达到最大值（4个）
	public static final long MAXNUMBEROFVISIBLEBANNERS = 340L;
	
	public static final long MODIFYBANKERROR=351L;

	public static final long TOkenError=352L;
	public static final long SignatureException =353L;
	public static final long MalFormedException =354L;
	public static final long UnsupportedTokenException =355L;
	public static final long TokenRequiredException =  359L;
	public static final long IllegalArugmentException =356L;

	/**
	/**
	 * 特殊码值
	 * 
	 * @author MG01886
	 *
	 */
	public static interface SPECIALERRORCODE {
		final long REALNAMEAUTHENTICATION = 3200L;// 请先跳转进行实名认证
		final long TOKENEXPIRED_LOGIN = 3100L;// token过期跳转重新登录
	}

}
