!function(a) {
    "function" == typeof define && define.amd ? define(["jquery"], a) : a(jQuery)
}(function(a) {
    function c(a) {
        return h.raw ? a : encodeURIComponent(a)
    }
    function d(a) {
        return h.raw ? a : decodeURIComponent(a)
    }
    function e(a) {
        return c(h.json ? JSON.stringify(a) : String(a))
    }
    function f(a) {
        0 === a.indexOf('"') && (a = a.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, "\\"));
        try {
            return a = decodeURIComponent(a.replace(b, " ")),
            h.json ? JSON.parse(a) : a
        } catch (a) {}
    }
    function g(b, c) {
        var d = h.raw ? b : f(b);
        return a.isFunction(c) ? c(d) : d
    }
    var b = /\+/g
      , h = a.cookie = function(b, f, i) {
        if (void 0 !== f && !a.isFunction(f)) {
            if (i = a.extend({}, h.defaults, i),
            "number" == typeof i.expires) {
                var j = i.expires
                  , k = i.expires = new Date;
                k.setDate(k.getDate() + j)
            }
            return document.cookie = [c(b), "=", e(f), i.expires ? "; expires=" + i.expires.toUTCString() : "", i.path ? "; path=" + i.path : "", i.domain ? "; domain=" + i.domain : "", i.secure ? "; secure" : ""].join("")
        }
        for (var l = b ? void 0 : {}, m = document.cookie ? document.cookie.split("; ") : [], n = 0, o = m.length; n < o; n++) {
            var p = m[n].split("=")
              , q = d(p.shift())
              , r = p.join("=");
            if (b && b === q) {
                l = g(r, f);
                break
            }
            b || void 0 === (r = g(r)) || (l[q] = r)
        }
        return l
    }
    ;
    h.defaults = {},
    a.removeCookie = function(b, c) {
        return void 0 !== a.cookie(b) && (a.cookie(b, "", a.extend({}, c, {
            expires: -1
        })),
        !a.cookie(b))
    }
}),
$(document).ready(function() {
    $.cookie("msgcookie") || ($("body").prepend("<div class='msgcookie'><p><a href='#' class='close'></a>Este sitio web utilizamos cookies de Google Analytics para analizar y mejorar nuestros servicios y elaborar información estadística. SI CONTINÚA NAVEGANDO, consideramos que ACEPTA SU USO. Puede obtener más información <a href='https://www.serdeagua.com/cookies' target='_blank'>AQUÍ.</a></p></div>"),
    $("body").on("click", ".close", function(a) {
        a.preventDefault(),
        $.cookie("msgcookie", "aceptado"),
        $(".msgcookie").fadeOut()
    }))
});
