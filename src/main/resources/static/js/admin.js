$(document).ready(function () {
    $("#login-form #login-btn").click(function () {
        var type = $(this).parents("form").find("select").val();
        window.location.href = "index-" + type + ".html";
        return false;
    });
    $(".side-nav li.active > a").click(function () {
        return false;
    });

    $(".user-modify").click(function () {
        $("#user-reset-form").hide();
        $("#user-form").show();
        var eID = $(this).parents("tr").find(".eID").text();
        var eName = $(this).parents("tr").find(".eName").text();
        var phoneNum = $(this).parents("tr").find(".phoneNum").text();
        var email = $(this).parents("tr").find(".email").text();
        var permission = $(this).parents("tr").find(".permission").text();

        $("#user-form .form-eID").val(eID);
        $("#user-form .form-eName").val(eName);
        $("#user-form .form-phoneNum").val(phoneNum);
        $("#user-form .form-email").val(email);
        $("#user-form input[name='form-permission']").each(function () {
            if ($(this).hasClass("selected")) {
                $(this).click().removeClass("selected");
            }
            var role = $(this).val();
            if (permission.indexOf(role) > -1) {
                $(this).click().addClass("selected");
            }
        });

        return false;
    });
    $(".user-reset").click(function () {
        $("#user-form").hide();
        $("#user-reset-form").show();

        return false;
    });

    $("#create-user").click(function () {
        $("#user-reset-form").hide();
        $("#user-form").show();

        $("#user-form .form-eID").val("");
        $("#user-form .form-eName").val("");
        $("#user-form .form-phoneNum").val("");
        $("#user-form .form-email").val("");
        $("#user-form input[name='form-permission']").each(function () {
            if ($(this).hasClass("selected")) {
                $(this).click().removeClass("selected");
            }
        });

        return false;
    });


    $(".info-modify").click(function () {
        $("#info-form").show();
        var stuID = $(this).parents("tr").find(".stuID").text();
        var stuName = $(this).parents("tr").find(".stuName").text();
        var stuClass = $(this).parents("tr").find(".stuClass").text();
        var stuEmail = $(this).parents("tr").find(".stuEmail").text();
        var stuPhone = $(this).parents("tr").find(".stuPhone").text();
        var teacher = $(this).parents("tr").find(".teacher").text();

        $("#info-form .form-stuID").val(stuID);
        $("#info-form .form-stuName").val(stuName);
        $("#info-form .form-stuClass").val(stuClass);
        $("#info-form .form-stuEmail").val(stuEmail);
        $("#info-form .form-stuPhone").val(stuPhone);
        $("#info-form .form-teacher").val(teacher);
        return false;
    });

    $("#create-info").click(function () {
        $("#info-form").show();

        $("#info-form .form-stuID").val("");
        $("#info-form .form-stuName").val("");
        $("#info-form .form-stuClass").val("");
        $("#info-form .form-stuEmail").val("");
        $("#info-form .form-stuPhone").val("");
        $("#info-form .form-teacher").val("");

        return false;
    });

    $(".graduator-modify").click(function () {
        $("#graduator-form").show();
        var stuName = $(this).parents("tr").find(".stuName").text();
        var company = $(this).parents("tr").find(".company").text();
        var salary = $(this).parents("tr").find(".salary").text();
        var comment = $(this).parents("tr").find(".comment").text();

        $("#graduator-form .form-stuName").val(stuName);
        $("#graduator-form .form-company").val(company);
        $("#graduator-form .form-salary").val(salary);
        $("#graduator-form .form-comment").val(comment);

        return false;
    });

    $("#create-graduator").click(function () {
        $("#graduator-form").show();

        $("#graduator-form .form-stuName").val("");
        $("#graduator-form .form-company").val("");
        $("#graduator-form .form-salary").val("");
        $("#graduator-form .form-comment").val("");

        return false;
    });

    $(".log-modify").click(function () {
        $("#log-form").show();
        var log = $(this).parents("tr").find(".log").text();

        $("#log-form .form-log").val(log);

        return false;
    });

    $("#create-log").click(function () {
        $("#log-form").show();

        $("#log-form .form-log").val("");

        return false;
    });

    $("#create-feedback").click(function () {
        $("#feedback-form").show();

        $("#feedback-form .form-stuName").val("");
        $("#feedback-form .form-date").val("");
        $("#feedback-form .form-comment").val("");

        return false;
    });

    if ($("body#score-tea").length > 0) {
        var cate = getUrlParam("cate");
        $("body#score-tea #" + cate).show();
    }

    // Salary Chart
    if ($("#salary-chart").length > 0) {
        Morris.Bar({
            element: 'salary-chart',
            data: [{
                className: '一班',
                salary: 6000
            }, {
                className: '二班',
                salary: 5500
            }, {
                className: '三班',
                salary: 7400
            }, {
                className: '四班',
                salary: 7200
            }, {
                className: '五班',
                salary: 6500
            }, {
                className: '六班',
                salary: 6000
            }],
            xkey: 'className',
            ykeys: ['salary'],
            labels: ['平均薪资'],
            barRatio: 0.4,
            xLabelAngle: 35,
            hideHover: 'auto',
            resize: true
        });
    }

    // Rank Chart
    if ($("#rank-chart").length > 0) {
        Morris.Bar({
            element: 'rank-chart',
            data: [{
                name: '张三',
                salary: 10000
            }, {
                name: '李四',
                salary: 9500
            }, {
                name: '王五',
                salary: 9500
            }, {
                name: '陈凯',
                salary: 9200
            }, {
                name: '王芳',
                salary: 9000
            }, {
                name: '陈俊',
                salary: 9000
            }],
            xkey: 'name',
            ykeys: ['salary'],
            labels: ['薪资'],
            barRatio: 0.4,
            xLabelAngle: 35,
            hideHover: 'auto',
            resize: true
        });
    }
    if ($(".form-date.extract-from-load").length > 0) {
        var today = new Date();
        today = (today.getYear() + 1900) + "/" + (today.getMonth() + 1) + "/" + today.getDate();
        $(".form-date.extract-from-load").val(today);
    }

    $("th.date.sortable").each(function () {
        var that = this;
        var index = $(that).index() + 1;
        $(that).closest("table").find("td:nth-child(" + index + ")").each(function () {
            var date_data = date_parser($(this).html());
            $(this).attr("alt", date_data);
        });
    });

    $("table thead .sortable").on("click", function (event) {
        var index = $(this).index() + 1;
        var $curr_table = $(this).closest("table");
        var sort_order = $(this).hasClass("ascend") ? "desc" : "asc";
        if ($(this).hasClass("date")) {
            $curr_table.find("tbody tr").tsort("td:nth-child(" + index + ")", {order: sort_order, attr: "alt"});
        } else {
            $curr_table.find("tbody tr").tsort("td:nth-child(" + index + ")", {order: sort_order});
        }
        $curr_table.find("thead th").removeClass("ascend descend");
        $curr_table.find("thead th i").removeClass("fa-sort-asc fa-sort-desc").addClass("fa-sort");
        $(this).addClass(sort_order + "end");
        $(this).find("i").removeClass("fa-sort").addClass("fa-sort-" + sort_order);

        return false;
    });
});

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var index = window.location.search.lastIndexOf("?");
    var r = window.location.search.substring(index + 1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}

/*=== Sort Start ===*/
(function (b) {
    var o = !1, d = null, u = parseFloat, j = String.fromCharCode, q = Math.min, l = /(-?\d+\.?\d*)$/g, g, a = [], h, m, t = 9472, f = {}, c;
    if (!Array.indexOf) {
        Array.prototype.indexOf = function (w) {
            for (var v = 0, s = this.length; v < s; v++) {
                if (this[v] == w) {
                    return v
                }
            }
            return -1
        }
    }
    for (var p = 32, k = j(p), r = 255; p < r; p++, k = j(p).toLowerCase()) {
        if (a.indexOf(k) !== -1) {
            a.push(k)
        }
    }
    a.sort();
    b.tinysort = {
        id: "TinySort",
        version: "1.3.27",
        copyright: "Copyright (c) 2008-2012 Ron Valstar",
        uri: "http://tinysort.sjeiti.com/",
        licenced: {
            MIT: "http://www.opensource.org/licenses/mit-license.php",
            GPL: "http://www.gnu.org/licenses/gpl.html"
        },
        defaults: {
            order: "asc",
            attr: d,
            data: d,
            useVal: o,
            place: "start",
            returns: o,
            cases: o,
            forceStrings: o,
            sortFunction: d,
            charOrder: g
        }
    };
    b.fn.extend({
        tinysort: function (V, L) {
            if (V && typeof(V) != "string") {
                L = V;
                V = d
            }
            var T = b.extend({}, b.tinysort.defaults, L), v, Q = this, z = b(this).length, ae = {}, W = !(!V || V == ""), H = !(T.attr === d || T.attr == ""), ah = T.data !== d, J = W && V[0] == ":", C = J ? Q.filter(V) : Q, F = T.sortFunction, s = T.order == "asc" ? 1 : -1, P = [];
            if (T.charOrder != g) {
                g = T.charOrder;
                if (!T.charOrder) {
                    m = false;
                    t = 9472;
                    f = {};
                    c = h = d
                } else {
                    h = a.slice(0);
                    m = false;
                    for (var S = [], B = function (i, ai) {
                        S.push(ai);
                        f[T.cases ? i : i.toLowerCase()] = ai
                    }, N = "", X = "z", aa = g.length, ac, Z, ad = 0; ad < aa; ad++) {
                        var x = g[ad], ab = x.charCodeAt(), I = ab > 96 && ab < 123;
                        if (!I) {
                            if (x == "[") {
                                var D = S.length, M = D ? S[D - 1] : X, w = g.substr(ad + 1).match(/[^\]]*/)[0], R = w.match(/{[^}]*}/g);
                                if (R) {
                                    for (ac = 0, Z = R.length; ac < Z; ac++) {
                                        var O = R[ac];
                                        ad += O.length;
                                        w = w.replace(O, "");
                                        B(O.replace(/[{}]/g, ""), M);
                                        m = true
                                    }
                                }
                                for (ac = 0, Z = w.length; ac < Z; ac++) {
                                    B(M, w[ac])
                                }
                                ad += w.length + 1
                            } else {
                                if (x == "{") {
                                    var G = g.substr(ad + 1).match(/[^}]*/)[0];
                                    B(G, j(t++));
                                    ad += G.length + 1;
                                    m = true
                                } else {
                                    S.push(x)
                                }
                            }
                        }
                        if (S.length && (I || ad === aa - 1)) {
                            var E = S.join("");
                            N += E;
                            b.each(E, function (i, ai) {
                                h.splice(h.indexOf(ai), 1)
                            });
                            var A = S.slice(0);
                            A.splice(0, 0, h.indexOf(X) + 1, 0);
                            Array.prototype.splice.apply(h, A);
                            S.length = 0
                        }
                        if (ad + 1 === aa) {
                            c = new RegExp("[" + N + "]", "gi")
                        } else {
                            if (I) {
                                X = x
                            }
                        }
                    }
                }
            }
            if (!F) {
                F = T.order == "rand" ? function () {
                    return Math.random() < 0.5 ? 1 : -1
                } : function (av, at) {
                    var au = o, am = !T.cases ? n(av.s) : av.s, ak = !T.cases ? n(at.s) : at.s;
                    if (!T.forceStrings) {
                        var aj = am && am.match(l), aw = ak && ak.match(l);
                        if (aj && aw) {
                            var ar = am.substr(0, am.length - aj[0].length), aq = ak.substr(0, ak.length - aw[0].length);
                            if (ar == aq) {
                                au = !o;
                                am = u(aj[0]);
                                ak = u(aw[0])
                            }
                        }
                    }
                    var ai = s * (am < ak ? -1 : (am > ak ? 1 : 0));
                    if (!au && T.charOrder) {
                        if (m) {
                            for (var ax in f) {
                                var al = f[ax];
                                am = am.replace(ax, al);
                                ak = ak.replace(ax, al)
                            }
                        }
                        if (am.match(c) !== d || ak.match(c) !== d) {
                            for (var ap = 0, ao = q(am.length, ak.length); ap < ao; ap++) {
                                var an = h.indexOf(am[ap]), i = h.indexOf(ak[ap]);
                                if (ai = s * (an < i ? -1 : (an > i ? 1 : 0))) {
                                    break
                                }
                            }
                        }
                    }
                    return ai
                }
            }
            Q.each(function (ak, al) {
                var am = b(al), ai = W ? (J ? C.filter(al) : am.find(V)) : am, an = ah ? "" + ai.data(T.data) : (H ? ai.attr(T.attr) : (T.useVal ? ai.val() : ai.text())), aj = am.parent();
                if (!ae[aj]) {
                    ae[aj] = {s: [], n: []}
                }
                if (ai.length > 0) {
                    ae[aj].s.push({s: an, e: am, n: ak})
                } else {
                    ae[aj].n.push({e: am, n: ak})
                }
            });
            for (v in ae) {
                ae[v].s.sort(F)
            }
            for (v in ae) {
                var ag = ae[v], K = [], Y = z, af = [0, 0], ad;
                switch (T.place) {
                    case"first":
                        b.each(ag.s, function (ai, aj) {
                            Y = q(Y, aj.n)
                        });
                        break;
                    case"org":
                        b.each(ag.s, function (ai, aj) {
                            K.push(aj.n)
                        });
                        break;
                    case"end":
                        Y = ag.n.length;
                        break;
                    default:
                        Y = 0
                }
                for (ad = 0; ad < z; ad++) {
                    var y = e(K, ad) ? !o : ad >= Y && ad < Y + ag.s.length, U = (y ? ag.s : ag.n)[af[y ? 0 : 1]].e;
                    U.parent().append(U);
                    if (y || !T.returns) {
                        P.push(U.get(0))
                    }
                    af[y ? 0 : 1]++
                }
            }
            Q.length = 0;
            Array.prototype.push.apply(Q, P);
            return Q
        }
    });
    function n(i) {
        return i && i.toLowerCase ? i.toLowerCase() : i
    }

    function e(v, x) {
        for (var w = 0, s = v.length; w < s; w++) {
            if (v[w] == x) {
                return !o
            }
        }
        return o
    }

    b.fn.TinySort = b.fn.Tinysort = b.fn.tsort = b.fn.tinysort
})(jQuery);

function date_parser(date_sent) {
    var day_extract = date_sent.split("/")[2];
    var month_extract = date_sent.split("/")[1];
    var year_extract = date_sent.split("/")[0];
    day_extract = (day_extract.length == 1) ? "0" + day_extract : day_extract;
    month_extract = (month_extract.length == 1) ? "0" + month_extract : month_extract;
    var parsable = year_extract + month_extract + day_extract;
    var date_number = Date.parse(parsable);
    return date_number;
}
/*=== Sort End ===*/

