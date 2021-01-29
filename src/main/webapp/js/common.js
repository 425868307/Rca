userType = null;
/**
 * 后台获取当前用户的账号
 */
function getAccount() {
    var account = "";
    $.ajax({
        type: "POST",
        url: "../getAccount",
        async: false,
        success: function (result) {
            account = result;
        }
    });
    return account;
}
/**
 * 查询详细信息
 * @param ammountSum
 */
function queryAccountDetail(ammountSum) {
    $.ajax({
        type: "POST",
        url: "queryAccountDetail",
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            "limit": ammountSum.limit,
            "year": ammountSum.year,
            "month": ammountSum.month,
            "day": ammountSum.day,
            "type": ammountSum.type
        }),
        success: function (resp) {
            if (resp.code == '0') {
                alert(resp.msg);
                return;
            } else if (resp.code == '-1') {
                alert(resp.msg);
                parent.location = '../login.jsp';
                return;
            }
            var list = resp.data;
            var th = "<tr><th>序号</th><th>年份</th><th>月份</th><th>日期</th>" +
                "<th>花费</th><th>收入</th><th>增量</th><th>操作</th></tr>";
            var tr = "";
            for (var i = 0; i < list.length; i++) {
                tr += "<tr><td>" + (i + 1) + "</td><td>" + list[i].year + "</td><td>" + list[i].month +
                    "</td><td>" + list[i].day + "</td><td class='num'>" + list[i].cost + "</td><td class='num'>" +
                    list[i].income + "</td><td class='num'>" + list[i].sub + "</td>" +
                    "<td><a href='#' onclick='detail(\"" + list[i].year + "," + list[i].month + "," + list[i].day +
                    "\")'>明细详情</a></td></tr>";
            }
            $('#datas').html(th + tr);
        },
        error: function (resp) {
            console.info(resp.responseText);
        }
    });
}

/**
 * 搜索功能查询所填日期的详细消费
 */
function search() {
    var year = $("input[name='year']").val();
    var month = $("input[name='month']").val();
    var day = $("input[name='day']").val();
    var type = $("input[name='type']:checked").attr("id");
    if (day != '') {
        type = 'day';
    } else if (month != '') {
        if (type == 'total') {
            type = 'month';
        } else if (type == 'detail') {
            type = 'day';
        }
    } else if (year != '') {
        if (type == 'total') {
            type = 'year';
        } else if (type == 'detail') {
            type = 'month';
        }
    } else {
        if (type == 'total') {
            type = 'year';
        } else if (type == 'detail') {
            type = 'day';
        }
    }
    queryAccountDetail({
        'type': type,
        'year': year,
        'month': month,
        'day': day
    });
}

/**
 * 根据年月日查询详细记录
 * @param ymd
 */
function detail(ymd) {
    var date = ymd.split(",");
    var year = date[0];
    var month = date[1];
    var day = date[2];
    if (day == '-') {
        if (month == '-') {
            queryAccountDetail({
                'type': 'month',
                'year': year
            });
        } else {
            queryAccountDetail({
                'type': 'day',
                'year': year,
                'month': month
            });
        }
    } else {
        queryDayDetail(year + '-' + month + '-' + day);
    }
}

/**
 * 查询具体某天的消费收入
 * @param ymd
 */
function queryDayDetail(happenDate) {
    $.ajax({
        type: "POST",
        url: "queryDayDetail",
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            "happenDate": happenDate
        }),
        success: function (resp) {
            if (resp.code == '0') {
                alert(resp.msg);
                return;
            } else if (resp.code == '-1') {
                alert(resp.msg);
                parent.location = '../login.jsp';
                return;
            }
            var list = resp.data;
            var th = "<tr><th>序号</th><th>日期</th>" +
                "<th>花费</th><th>收入</th><th>费用描述</th><th>费用产生时间</th><th colspan='2'>操作</th></tr>";
            var tr = "";
            for (var i = 0; i < list.length; i++) {
                var fun = "editDayDetail(this,'" + list[i].id + "')";
                tr += "<tr><td>" + (i + 1) + "</td><td>" + list[i].year + "-" + list[i].month + "-" + list[i].day +
                    "</td><td class='num'>" + list[i].cost + "</td><td class='num'>" +
                    list[i].income + "</td><td class='num'>" + list[i].desc + "</td><td>" + list[i].happenTime + "</td>" +
                    "<td><a href='#' onclick=\"" + fun + "\">修改</a></td>" +
                    "<td><a href='#' onclick='deleteDayDetail(\"" + list[i].id + "\",\"" + happenDate + "\")'>删除</a></td></tr>";
            }
            $('#datas').html(th + tr);
        },
        error: function () {
            window.alert("登陆失败!");
        }
    });
}

/**
 * 编辑
 * @param id
 */
function editDayDetail(btn, id) {
    var tds = $(btn).parents('tr').children('td');
    $('#title').html("修改:");
    $('#select').hide();
    var div = "<div>日期:</div><div>" + tds.eq(1).text() +
        "</div><div>时间:</div><div>" + tds.eq(5).text() +
        "</div><div>花费:</div><input type='text' value='" + tds.eq(2).text() + "'>" +
        "<div>收入:</div><input type='text' value='" + tds.eq(3).text() + "'>" +
        "<div>描述:</div><input type='text' value='" + tds.eq(4).text() + "'>" +
        "<input type='button' value='确定' onclick='saveDayDetail(" + id + ")'>" +
        "<input type='button' value='取消' onclick='returnDayDetail(\"" + tds.eq(1).text() + "\")'>";
    $('#edit').html(div);
    $('#datas').html("");
}

/**
 * 删除详细记录信息
 * @param id
 */
function deleteDayDetail(id, happenDate) {
    var res = confirm("确定删除此条记录？");
    if (!res) {
        return;
    }
    $.ajax({
        type: "POST",
        url: "deleteDayDetailById",
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            "id": id
        }),
        success: function (resp) {
            alert(resp.msg);
            if (resp.code == '-1') {
                parent.location = '../login.jsp';
            } else {
                queryDayDetail(happenDate);
            }
        },
        error: function () {
            alert("系统异常.删除失败")
        }
    });
}

/**
 * 修改保存
 */
function saveDayDetail(id) {
    var cost = $("#edit input :eq(0)").val();
    var income = $("#edit input :eq(1)").val();
    var desc = $("#edit input :eq(2)").val();

    $.ajax({
        type: "POST",
        url: "saveDayDetailById",
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({
            "income": income,
            "cost": cost,
            "desc": desc,
            "id": id
        }),
        success: function (resp) {
            alert(resp.msg);
            $('#edit input :eq(4)').click();
        },
        error: function () {
            alert("系统异常.修改失败")
        }
    });
}

/**
 * 返回明细显示
 */
function returnDayDetail(happenDate) {
    $('#title').html("统计:");
    $('#select').show();
    $('#edit').html("");
    queryDayDetail(happenDate);
}


i = 1;
/**
 * 新增记录添加明细
 */
function addDetail() {
    var bt = "";
    if (i < 10) {
        bt = "&nbsp&nbsp" + i;
    } else {
        bt = i;
    }
    var html = "<tr><td>" + bt + "&nbsp;&nbsp;时间:<input type='text' class='txt selecttime' readonly " +
        "value='' id='dtime" + i + "'/></td>" +
        "<td>花费:<input name='cost' type='text'/></td>" +
        "<td>收入:<input name='income' type='text'></td>" +
        "<td>备注:<input name='desc' type='text'></td>" +
        "<td><a href='#' onclick='deleteDetail(this)'>删除</a></td></tr>";
    $('#add').append(html);
    selectime();
    i++;
}

/**
 * 明细保存
 */
function saveDetail() {
    var detailSum = $('#add tr').length;
    if (detailSum == 0) {
        alert("请添加明细。");
        return;
    }
    var details = [];
    var date = $('#date').val();
    for (var a = 0; a < detailSum; a++) {
        var detail = {};
        var tdetail = $('#add tr').eq(a).find('td');
        detail['happenDate'] = date;
        detail['happenTime'] = tdetail.eq(0).find('input').val();
        var cost = tdetail.eq(1).find('input').val();
        var income = tdetail.eq(2).find('input').val();

        detail['cost'] = cost == '' ? '0' : cost;
        detail['income'] = income == '' ? '0' : income;
        detail['desc'] = tdetail.eq(3).find('input').val();
        details.push(detail);
    }
    console.info(details);
    $.ajax({
        type: "POST",
        url: "addAccountDetail",
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(details),
        success: function (result) {
            alert(result.msg);
            if (result.code == '1') {
                $('#add').html("");
                i = 1;
                $('#menu button :eq(0)').removeAttr("disabled");
            } else if (resp.code == '-1') {
                alert(resp.msg);
                parent.location = '../login.jsp';
                return;
            }
        },
        error: function () {
            alert("失败");
        }
    });
}
/**
 * 清空所有添加的明细
 */
function clearDetail() {
    $('#add').html("");
    i = 1;
    $('#menu button :eq(0)').removeAttr("disabled");
}

/**
 * 明细删除
 * @param moveDoc 需要删除的节点
 */
function deleteDetail(moveDoc) {
    $(moveDoc).parents('tr').remove();
    i = 1;
    $('#menu button :eq(0)').attr("disabled", "disabled");
}

/**
 * 密码修改
 */
function psdEdit() {
    $('body a').hide();
    $('#userInfo').hide();
    $('#psdEdit').show();
}
/**
 * 个人信息添加或者修改
 */
function infoAddEdit() {
    $('body a').hide();
    $('#userInfo').hide();
    $('#infoEdit').show();
}
/**
 * 普通用户信息查询
 */
function usualUser() {
    if (reLogin()) {
        var users = queryAccountByPower(9);
        if (users == null) {
            alert("没有查询到普通用户信息.");
            return;
        }
        for (var i = 0; i < users.length; i++) {
            var name = users[i].name;
            if (name == null) {
                name = '';
            }
            var append = "<tr><td><input type='checkbox' value='" + users[i].id + "'></td>" +
                "<td class='account'>" + users[i].account + "</td><td hidden='hidden'>" + users[i].password +
                "</td><td>" + name + "</td><td>" + users[i].age + "</td></tr>";
            $('#usualUserEdit table').append(append);
        }
        $('a').hide();
        $('#usualUserEdit').show();
    } else {
        alert("密码错误,您无法进行此操作.")
    }
}

/**
 * 系统管理员信息查询
 */
function systemManage() {
    if (reLogin()) {
        var managers = queryAccountByPower(1);
        if (managers == null) {
            alert("没有查询到系统管理员信息.");
            return;
        }
        for (var i = 0; i < managers.length; i++) {
            var name = managers[i].name;
            if (name == null) {
                name = '';
            }
            var append = "<tr><td><input type='checkbox' value='" + managers[i].id + "'></td>" +
                "<td class='account'>" + managers[i].account + "</td><td hidden='hidden'>" + managers[i].password +
                "</td><td>" + name + "</td><td>" + managers[i].age + "</td></tr>";
            $('#managerEdit table').append(append);
        }
        $('a').hide();
        $('#managerEdit').show();
    } else {
        alert("密码错误,您无法进行此操作.")
    }
}
/**
 * 用户登录
 */
function reLogin() {
    var psd = prompt("请输入您的密码?");
    var account = getAccount();
    if (psd == '' || psd == null) {
        return false;
    } else {
        var res = false;
        $.ajax({
            type: "POST",
            url: "../login",
            dataType: "json",
            async: false,
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify({
                "account": account,
                "password": psd
            }),
            success: function (result) {
                if (result.code == '1') {
                    res = true;
                }
            }
        });
        return res;
    }
}

/**
 * 编辑个人信息取消
 */
function editCancel() {
    location.reload();
}

function infoSave() {
    var userInfo = $('#infoEdit ul li input');
    var name = userInfo.eq(0).val();
    var age = userInfo.eq(1).val();
    var sex = $("#infoEdit ul li input[name='sex']:checked").val();
    var birthdate = userInfo.eq(4).val();
    var addr = userInfo.eq(5).val();
    var phone = userInfo.eq(6).val();
    var email = userInfo.eq(7).val();
    var user = {
        "name": name,
        "age": age,
        "sex": sex,
        "birthdate": birthdate,
        "addr": addr,
        "phone": phone,
        "email": email
    };
    $.ajax({
        type: "POST",
        url: "../saveUserInfo",
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(user),
        success: function (resp) {
            alert(resp.msg);
            if (resp.code == '1') {
//				parent.location.reload();
//				parent.$('#userInfo').click();
                editCancel();
            }
            ;
            if (resp.code == '-1') {
                parent.location = '../login.jsp';
            }
            if (resp.code == '0') {
                alert("保存失败.");
            }
        },
        error: function () {
            window.alert("保存失败...");
        }
    });
}

function psdEditSave() {
    var passwords = $('#psdEdit ul li input');
    var newPsd = passwords.eq(1).val();
    var repPsd = passwords.eq(2).val();
    if (newPsd != repPsd) {
        alert("重复密码与新密码不一致.");
        return;
    }
    var oldPsd = passwords.eq(0).val();
    if (newPsd == oldPsd) {
        alert("新密码不能与原始密码一致.");
        return;
    }
    $.ajax({
        type: "POST",
        url: "../updatePassword",
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({"oldPsd": oldPsd, "newPsd": newPsd}),
        success: function (resp) {
            if (resp.code == '1') {
                alert(resp.msg);
                editCancel();
            }
            ;
            if (resp.code == '-1') {
                parent.location = '../login.jsp';
            }
            if (resp.code == '0') {
                alert(resp.msg + "保存失败.");
            }
        },
        error: function () {
            window.alert("保存失败...");
        }
    });
}

/**
 * 通过权限查询账号
 */
function queryAccountByPower(power) {
    var list = null;
    $.ajax({
        type: "POST",
        url: "../queryAccountByPower",
        dataType: "json",
        async: false,
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({"power": power}),
        success: function (resp) {
            if (resp.code == '1') {
                list = resp.data;
            }
            ;
            if (resp.code == '-1') {
                parent.location = '../login.jsp';
            }
            if (resp.code == '0') {
                alert(resp.msg + ",查询失败.");
            }
        },
        error: function () {
            window.alert("查询失败...");
        }
    });
    return list;
}

function userAdd(type) {
    userType = type;
    if (type == '9') {
        $('.account-add .theme-poptit h3').html("用户添加");
    }
    if (type == '1') {
        $('.account-add .theme-poptit h3').html("系统管理员添加");
    }
    $('.theme-popover-mask').fadeIn(100);
    $('.account-add').slideDown(200);
}

function userDelete(type) {
    var users = $("tr td input[type='checkbox']:checked");
    if (users.length != 1) {
        alert("删除失败,删除暂不支持批量操作.")
        return;
    }
    var ensure;
    if (type == '1') {
        var ensure = confirm("是否确定删除此管理员？");
    }
    if (type == '9') {
        var ensure = confirm("是否确定删除此用户？");
    }
    if (!ensure) {
        return;
    }
    var id = users.eq(0).val();
    console.info("删除id:" + id);
    $.ajax({
        type: "POST",
        url: "../deleteAccountById",
        data: {"id": id},
        dataType: "json",
        success: function (resp) {
            alert(resp.msg);
            if (resp.code == '1') {
                users.eq(0).parents('tr').remove();
            }
            ;
            if (resp.code == '-1') {
                parent.location = '../login.jsp';
            }
        },
        error: function () {
            window.alert("删除失败...");
        }
    });
}

function psdCheck(type) {
    var users = $("tr td input[type='checkbox']:checked");
    if (users.length != 1) {
        alert("必须选定1条且只能选定1条进行此操作.")
        return;
    }
    var prop = users.eq(0).parents('tr').find('td');
    $(".password-info .theme-signin tt").eq(0).html(prop.eq(1).text());
    $(".password-info .theme-signin tt").eq(1).html(prop.eq(2).text());
    $(".password-info .theme-signin li input").eq(0).val(prop.eq(2).text());

    $(".password-info .theme-signin tt").eq(1).show();		//显示密码显示框
    $(".password-info .theme-signin li input").eq(0).hide();	//隐藏密码修改框

    $(".password-info .theme-signin li input").eq(1).show();	//显示修改按钮
    $(".password-info .theme-signin li input").eq(2).hide();	//隐藏保存按钮
    $('.theme-popover-mask').fadeIn(100);
    $(".password-info").animate({left: '50%', opacity: 'show'}, 500);
}

function editUserPsd() {
    $(".password-info .theme-signin tt").eq(1).hide();		//显示密码显示框
    $(".password-info .theme-signin li input").eq(0).show();	//隐藏密码修改框

    $(".password-info .theme-signin li input").eq(1).hide();	//显示修改按钮
    $(".password-info .theme-signin li input").eq(2).show();	//隐藏保存按钮
}

function saveUserPsd() {
    var account = $(".password-info tt").eq(0).text();
    var psd = $(".password-info .theme-signin li input").eq(0).val();
    $.ajax({
        type: "POST",
        url: "../updatePasswordByAccount",
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify({"account": account, "password": psd}),
        success: function (resp) {
            alert(resp.msg);
            if (resp.code == '1') {
                $("tr td input[type='checkbox']:checked").eq(0).parents('tr').find('td').eq(2).text(psd);
                $('.theme-popover-mask').fadeOut(100);
                $(".password-info").animate({opacity: 'hide'}, 500);
            }
            ;
            if (resp.code == '-1') {
                parent.location = '../login.jsp';
            }
        },
        error: function () {
            window.alert("修改用户密码失败...");
        }
    });
}

/** 用户添加保存*/
function saveRcaUserInfo() {
    var pwd1 = $(".account-add input[name='pwd']").val();
    var pwd2 = $(".account-add input[name='repwd']").val();
    if (pwd1 != pwd2) {
        alert("两次密码不一致");
        return;
    } else if (pwd1 == '' && pwd1 == '') {
        alert("请输入密码");
        return;
    }
    var pwd = pwd1;
    var account = $(".account-add input[name='account']").val();
    var name = $(".account-add input[name='name']").val();
    var age = $(".account-add input[name='age']").val();
    var sex = $(".account-add input[name='sex']:checked").val();

    var user = {
        "account": account,
        "password": pwd,
        "age": age,
        "sex": sex,
        "name": name,
        "power": userType
    };
    $.ajax({
        type: "POST",
        url: "../addRcaUserInfo",
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        data: JSON.stringify(user),
        success: function (resp) {
            console.info(resp);
            alert(resp.msg);
            if (resp.code == '1') {
                resetRcaUserInfo();
                $('.theme-popover-mask').fadeOut(100);
                $('.account-add').slideUp(200);

                var module = null;
                if (userType == '1') {
                    module = $('#managerEdit table');
                } else if (userType == '9') {
                    module = $('#usualUserEdit table');
                }
                module.find("tr").remove();
                var head = "<tr><td style=\"width: 15px;\"></td><td class=\"account\">用户账号</td>" +
                    "<td hidden=\"hidden\">密码</td><td>姓名</td><td>年龄</td></tr>";
                module.append(head);
                var managers = queryAccountByPower(userType);
                if (managers == null) {
                    alert("没有查询到系统管理员信息.");
                    return;
                }
                for (var i = 0; i < managers.length; i++) {
                    var name = managers[i].name;
                    if (name == null) {
                        name = '';
                    }
                    var append = "<tr><td><input type='checkbox' value='" + managers[i].id + "'></td>" +
                        "<td class='account'>" + managers[i].account + "</td><td hidden='hidden'>" + managers[i].password +
                        "</td><td>" + name + "</td><td>" + managers[i].age + "</td></tr>";
                    module.append(append);
                }
            }
        },
        error: function () {
            window.alert("用户添加失败");
        }
    });
}

function checkAccount() {   //检测账号是否可用
    var account = $(".account-add input[name='account']").val();
    if (account == '') {
        $(".account-add .hint").text("用户名不能为空").css("color", "red");
        $(".account-add input[type='button']").eq(0).attr("disabled", "disabled");
        return;
    }
    $.ajax({
        type: "POST",
        url: "../checkAccount",
        data: {"account": account},
        success: function (result) {
            console.info(result);
            if (result.code == '1') {
                $(".account-add .hint").text(result.msg).css("color", "green");
                $(".account-add input[type='button']").eq(0).removeAttr("disabled");
            } else {
                $(".account-add .hint").text(result.msg).css("color", "red");
                $(".account-add input[type='button']").eq(0).attr("disabled", "disabled");
            }
        }
    });
}

function resetRcaUserInfo() {
    $(".account-add .hint").text("");
    $(".account-add ol li input[name='account']").val("");
    $(".account-add ol li input[name='pwd']").val("");
    $(".account-add ol li input[name='repwd']").val("");
    $(".account-add ol li input[name='name']").val("");
    $(".account-add ol li input[name='age']").val("");
    $(".account-add ol li input[name='sex'] ").eq(0).attr('checked', 'checked');
}
