package com.simon.backstage.constant;

/**
 * @author fengtianying
 * @date 2019/1/27 13:26
 */
public interface Status {
        //欠费
        int chargeItemRecordOwe = 0;
        //已支付
        int chargeItemRecordPaid = 1;
        //预支付
        int chargeItemRecordAdvance = 2;

        //出账
        int advanceTypeOut = 0;
        //入账
        int advanceTypeIn = 1;

        //广告图片
        int advertisementTypeImg = 0;
        //广告视频
        int advertisementTypeVideo = 1;

        //资产进库
        int storageTypeIn = 0;
        //资产出库
        int storageTypeOut = 1;

        //收费模式  0周期性收费
        int itemBillingModePeriodicity = 0;
        //1临时性收费
        int itemBillingModeTemporary = 1;

        //收费方式0固定收费1公式收费
        int alculationMethodFixed = 0;
        int alculationMethodFormula = 1;
        //收费状态0欠费1已付2预支付
        int recordStatusOwe = 0;
        int recordStatusPaid = 1;
        int recordStatusPre = 2;

        //事件进度0开始1待定2完成
        int eventStatusAction = 0;
        int eventStatusStandby = 1;
        int eventStatusFinished = 2;

        //单元状态0空置1租赁2装修中3入住
        int unitStatusVacancy = 0;
        int unitStatusRent = 1;
        int unitStatusDecoration = 2;
        int unitStatusUsed = 3;

        //通告类型0触摸板1app
        int noticeTypeTouch = 0;
        int noticeTypeApp = 1;
}
