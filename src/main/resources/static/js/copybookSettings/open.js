$(document).ready(function(){


    // 获取编辑信息
    $.ajax({
        url: '/copyWriter/getCopyWriterById',
        data: {'cwId': $('input[name = "copyWriterId"]').val()},
        dataType: 'JSON',
        success: function (data) {
            if(data.data != null){
                console.log(data);
                $('.title h3').html(data.data.cwName);

                $('.content').html(data.data.content);

            }
        }
    })


});