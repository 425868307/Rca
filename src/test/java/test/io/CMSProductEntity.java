//package test.io;
//
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Document(collection = "ExhibitTable")
//public class CMSProductEntity extends BaseCMSEntity {
//
//    @Field("_id")
//    private String id;
//
//    /**
//     * 展商id
//     */
//    @Field("ExhibitorId")
//    private String exhibitorId = "";
//
//    /**
//     * 展品名称
//     */
//    @Field("Name")
//    private String name = "";
//
//    /**
//     * 展品名称 英文
//     */
//    @Field("NameEn")
//    private String nameEn = "";
//
//    /**
//     * 产品分类
//     */
//    @Field("Cate")
//    private List<CMSLabelModel> cate = new ArrayList<>();
//
//    /**
//     * 产品code
//     */
//    @Field("Code")
//    private String code = "";
//
//    /**
//     * 产品logo
//     */
//    @Field("Logo")
//    private List<CMSProductResourceModel> logo = new ArrayList<>();
//
//    /**
//     * 产品图片
//     */
//    @Field("Pics")
//    private List<CMSProductResourceModel> pics = new ArrayList<>();
//
//    /**
//     * 视频
//     */
//    @Field("Video")
//    private List<CMSProductResourceModel> video = new ArrayList<>();
//
//
//    /**
//     * 3D链接
//     */
//    @Field("ThreeD")
//    private String threeD;
//
//    /**
//     * 产品简介
//     */
//    @Field("Desc")
//    private String desc;
//
//    /**
//     * 产品简介en
//     */
//    @Field("DescEn")
//    private String descEn;
//
//    /**
//     * 产品型号
//     */
//    @Field("Model")
//    private String model;
//
//    @Field("ModelEn")
//    private String modelEn;
//
//    /**
//     * 产品材质
//     */
//    @Field("Material")
//    private String material;
//
//    @Field("MaterialEn")
//    private String materialEn;
//
//    /**
//     * 产品颜色
//     */
//    @Field("Color")
//    private String color;
//
//    @Field("ColorEn")
//    private String colorEn;
//
//    /**
//     * 产品尺寸
//     */
//    @Field("Size")
//    private String size;
//
//    /**
//     * 展品特征 本公司主打产品 = 1,本公司新产品_近一年内研发产品 = 2, 智能产品 = 3,绿色环保 = 4,自主知识产权 = 5
//     */
//    @Field("Feature")
//    private List<String> feature = new ArrayList<>();
//
//    /**
//     * 主要目标市场
//     */
//    @Field("MainMarkets")
//    private List<String> mainMarkets = new ArrayList<>();
//
//    /**
//     * 其他目标市场
//     */
//    @Field("MarketsOthers")
//    private String marketsOthers;
//
//    /**
//     * 最低价格
//     */
//    @Field("MinPrice")
//    private BigDecimal minPrice = BigDecimal.ZERO;
//
//    /**
//     * 最高价格
//     */
//    @Field("MaxPrice")
//    private BigDecimal maxPrice = BigDecimal.ZERO;
//
//    /**
//     * 货币类型（无 = 0，人民币 = 1,美元 = 2）
//     */
//    @Field("Currency")
//    private int currency;
//
//    /**
//     * 是否CF奖
//     */
//    @Field("IsCf")
//    private int isCF;
//
//    /**
//     * 上架状态(未知 = 0,待审核 = 1,待上架 = 2,已上架 = 3,已下架 = 4,已禁用 = 5,需修改信息 = 6)
//     */
//    @Field("Status")
//    private int status;
//
//    /**
//     * 上架时间
//     */
//    @Field("UpTime")
//    private Date upTime;
//
//    /**
//     * 排序
//     */
//    @Field("Sort")
//    private int sort = 0;
//
//    /**
//     * 产品分组
//     */
//    @Field("Group")
//    private List<Integer> group = new ArrayList<>();
//
//    /**
//     * 原产地证明
//     */
//    @Field("OriginProvement")
//    private List<CMSFileViewModel> originProvement = new ArrayList<>();
//
//    /**
//     * 起订量
//     */
//    @Field("Moq")
//    private long moq = 0L;
//
//    /**
//     * 关键词
//     */
//    @Field("Keywords")
//    private List<String> keywords = new ArrayList<>();
//
//    @Field("KeywordsEn")
//    private List<String> keywordsEn = new ArrayList<>();
//
//    /**
//     * CF获奖者
//     */
//    @Field("IsCfWinner")
//    private int isCfWinner = 0;
//
//    /**
//     * 企业banner
//     */
//    /*@Field("EnterpriseBanner")
//    private List<CMSProductResourceModel> enterpriseBanner = new ArrayList<>();*/
//
//    /**
//     * cf获奖等级（未知 = 0,至尊金奖 = 1,金奖 = 2,银奖 = 3,铜奖 = 4）
//     */
//    @Field("CfPrizeGrade")
//    private int cfPrizeGrade = 0;
//
//    /**
//     *  全部 = 0,
//     *  CF奖待审核 = 1,
//     *  CF奖审核通过 = 2,
//     *  CF奖审核拒绝 = 3
//     */
//    private int CfApproveStatus;
//
//    /**
//     * 是否公开
//     * 公开 = 1
//     * 非公开 = 0
//     */
//    @Field("IsOpen")
//    private int isOpen;
//
//    @Field("IsNew")
//    private int isNew;
//
//    @Field("Price")
//    private String price;
//
//    @Field("UnitChinese")
//    private String unitChinese;
//
//    @Field("UnitEnglish")
//    private String unitEnglish;
//
//    @Field("Tags")
//    private int tags;
//
//    @Field("BanReasons")
//    private String banReasons;
//
//    /**
//     * 所属组织ID
//     */
//    private String BelongOrgId;
//
//    /**
//     * 所属组织ID
//     */
//    private List<String> BelongOrgIds;
//
//
//}
