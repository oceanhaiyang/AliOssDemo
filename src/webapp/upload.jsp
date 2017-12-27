<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
         language="java" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/meta.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>图片详情</title>

    <script type="text/javascript" src="${ctx}/js/plugins/jquery/ajaxfileupload.js"></script>



    <script type="text/javascript">

        function uploadPic(){
            //alert("1");
            $.ajaxFileUpload({
                url:ctutil.bp() + '/check/check!simpleFileupload.action?check.taskId=${check.taskId}',
                fileElementId:'file',
                dataType:'json',
                success :function (data) {
                    if(data.resultCode=='200'){

                        $.messager.alert('提示','上传成功');
                    }
                    else if(data.resultCode=='600'){
                        $.messager.alert('提示','上传失败');
                    }
                }
            })

        }




    </script>


</head>
<body>
<div class="over-content">
    <form id="myForm" method="post" enctype="multipart/form-data">


        <div class="over-cell" id="uploadBtn">
            <label>请选择：</label>

            <span class="over-button" style="position:relative">选择
                    <input id="file" type="file" name="fileFileName" onchange="uploadPic()"/>
                </span>

        </div>

        <input type="hidden" name="check.taskId" id="checkId" value="${check.taskId}"/>
        <input type="hidden" name="check.carId" id="carId" value="${check.carId}"/>
    </form>
</div>
</body>
</html>
