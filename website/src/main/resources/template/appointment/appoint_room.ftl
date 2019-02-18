<div class="fix-box pay-box1" id="appoint_room_model">
    <div class="model-box">
        <div class="model-top flex-between">
            <h3>预约看房</h3>
            <img src="${resourcesPath}/resources/img/cc.png" alt="" class="close" id="close_appoint_room_model">
        </div>
        <div class="model-content form-list">
            <div class="flex-between mb28">
                <div class="flex w-50">
                    <p class="color27 font14 w100 bold-s">预约社区</p>
                    <select name="hotelProject" id="hotelProject" class="color27 font14 w240">
                    <#--<option value="">乐璟服务公寓</option>-->
                    </select>
                </div>
                <div class="flex w-50">
                    <p class="color27 font14 w100 bold-s">预约房型</p>
                    <select name="roomType" id="roomType" class="color27 font14 w240">
                    <#--<option value="">单人间</option>-->
                    </select>
                </div>
            </div>
            <div class="mb28 flex">
                <p class="color27 font14 w100 bold-s">预计入住日期</p>
                <input type="text" name="predictCheckInTime" class="demo-input w654 font14" placeholder="年/月/日"
                       id="checkInTimeId" oninput="aaa()">
                <div style="display: none;" class="text-error mobileVerify" id="predictCheckInTimeTip">
                    请选择预计入住日期
                </div>
            </div>
            <div class="mb28 flex">
                <p class="color27 font14 w100 bold-s">姓名</p>
                <input type="text" name="checkInUserName" id="checkInUserName" class="demo-input w654 font14"
                       placeholder="" maxlength="10" oninput="aaa()">
                <div style="display: none;" class="text-error mobileVerify" id="checkInUserNameTip">
                    请输入姓名
                </div>
            </div>
            <div class="mb28 flex">
                <p class="color27 font14 w100 bold-s">手机号码</p>
                <input type="text" name="checkInUserPhone" id="checkInUserPhone" class="demo-input w654 font14"
                       placeholder="" maxlength="11" oninput="value=value.replace(/[^\d]/g,''),aaa()" >
                <div style="display: none;" class="text-error mobileVerify" id="checkInUserPhoneTip">
                    请输入手机号码
                </div>
            </div>
            <div class="mb28 flex">
                <p class="color27 font14 w100 bold-s">微信号</p>
                <input type="text" name="checkInUserWeixin" id="checkInUserWeixin" class="demo-input w654 font14"
                       placeholder="" maxlength="30">
            </div>
            <div class="mt86">
                <button class=" bggray" type="button" onclick="submitAppointRoom()" disabled="disabled"
                        id="submit_appoint_room">提交
                </button>
            <#--<p class="color48 font14 bold-s">提交失败，请稍后再试...</p>-->
            </div>

        </div>

    </div>
</div>

<#include "../appointment/appoint_success.ftl">
 <script>
     var dataList = [];
     lay('#version').html('-v' + laydate.v);
     //执行一个laydate实例
     laydate.render({
         elem: '#checkInTimeId',
         position: "fixed",
         min: 0
     });
     $(function () {
         openModel();
         $("#hotelProject").on("change", function (data) {
             $('#roomType').empty();
             var selectResult = data.currentTarget.value;
             for (var i = 0; i < dataList.length; i++) {
                 if (dataList[i].projectId == selectResult) {
                     var roomTypeLsit = dataList[i].roomTypes;
                     for (var j = 0; j < roomTypeLsit.length; j++) {
                         var option = document.createElement("option");
                         $(option).val(roomTypeLsit[j].id);
                         $(option).text(roomTypeLsit[j].roomTypeName);
                         $('#roomType').append(option);
                     }
                 }
             }
         });
     })

     var openModel = function () {
         $('#hotelProject').empty();
         $('#roomType').empty();
         // 拉取社区
         $.HTTP.Get($.HTTP.URL.home_projects,
                 {},
                 function (response) {
                     dataList = response.projects;
                     for (var i = 0; i < dataList.length; i++) {
                         if(dataList[i].projectName == "仅供测试使用社区"){
                             // 处理生产上的 测试社区
                         }else{
                             var option = document.createElement("option");
                             $(option).val(dataList[i].projectId);
                             $(option).text(dataList[i].projectName);
                             $('#hotelProject').append(option);
                             // 社区数组第一项
                             if (i == 0) {
                                 var roomTypeLsit = dataList[i].roomTypes;
                                 for (var j = 0; j < roomTypeLsit.length; j++) {
                                     var option = document.createElement("option");
                                     $(option).val(roomTypeLsit[j].id);
                                     $(option).text(roomTypeLsit[j].roomTypeName);
                                     $('#roomType').append(option);
                                 }
                             }
                         }
                     }
                 },function (error) {
                     console.log(error)
                 }
         )
     }

     var aaa = function () {
         var predictCheckInTime = $("#checkInTimeId").val();
         var checkInUserName = $("#checkInUserName").val();
         var checkInUserPhone = $("#checkInUserPhone").val();
         if (predictCheckInTime != '' && checkInUserName != '' && checkInUserPhone != '') {
             $("#submit_appoint_room").addClass("bgblue");
             $('#submit_appoint_room').removeAttr("disabled");

         }
     }

     // 提交表单并验证
     var submitAppointRoom = function () {
         var isSubmit = true;
         var hotelProject = $("#hotelProject").val();
         var roomType = $("#roomType").val();
         var predictCheckInTime = $("#checkInTimeId").val();
         if (predictCheckInTime == '') {
             isSubmit = false;
         }
         var checkInUserName = $("#checkInUserName").val();
         if (checkInUserName == '') {
             isSubmit = false;
         }
         var checkInUserPhone = $("#checkInUserPhone").val();
         if (checkInUserPhone == '') {
             isSubmit = false;
         }
         var checkInUserWeixin = $("#checkInUserWeixin").val();
         var params = {
             "projectId": hotelProject,
             "roomTypeId": roomType,
             "checkInTime": timeHandle(predictCheckInTime),
             "checkInName": checkInUserName,
             "checkInPhone": checkInUserPhone,
             "checkInWeixin": checkInUserWeixin,
         }
         if (isSubmit) {
             appointRoom(params);
         }
     }

     var timeHandle = function (date) {
         date = date.replace(/-/g, '/');
         return new Date(date);
     }

     // 发起请求
     var appointRoom = function (params) {
         $.HTTP.Post($.HTTP.URL.home_appoint_room,
                 params,
                 function (response) {
                     if (response && response.result == "success") {
                         $("#appoint_room_model").hide();
                         $("#appoint_success_model").show();
                         cleanForm();
                     }
                 }
         )
     }
     // 清空表单
     var cleanForm = function () {
         $("#checkInTimeId").val("");
         $("#checkInUserName").val("");
         $("#checkInUserPhone").val("");
         $("#checkInUserWeixin").val("")
         $("#predictCheckInTimeTip").css("display", "none");
         $("#checkInUserNameTip").css("display", "none");
         $("#checkInUserPhoneTip").css("display", "none");
         $("#submit_appoint_room").removeClass("bgblue");
         $('#submit_appoint_room').attr("disabled", "disabled");
         $('#roomType').empty();
     }

     $(function () {
         $("#close_appoint_room_model").click(function () {
             cleanForm();
             $("#appoint_room_model").hide();
         });
     })


 </script>