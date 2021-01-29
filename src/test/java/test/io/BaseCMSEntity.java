//package test.io;
//
//import org.springframework.data.mongodb.core.mapping.Field;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import java.util.Date;
//
//
//public class BaseCMSEntity {
//
//    /**
//     * 创建人ID
//     */
//    /*@Field("CreatedUserId")
//    private String createdUserId = "";*/
//
//    /**
//     * 创建时间
//     */
//    /*@Field("CreatedDate")
//    @DateTimeFormat(
//            pattern = "yyyy-MM-dd HH:mm:ss"
//    )
//    private Date createdDate;*/
//
//    /**
//     * 最后修改人ID
//     */
//    /*@Field("LatestUpdatedUserId")
//    private String latestUpdatedUserId = "";*/
//
//    /**
//     * 最后修改时间
//     */
//    @Field("LatestUpdatedDate")
//    @DateTimeFormat(
//            pattern = "yyyy-MM-dd HH:mm:ss"
//    )
//    private Date latestUpdatedDate;
//
//    @Field("IsDeleted")
//    private boolean isDelete = false;
//
//}
