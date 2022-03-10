<%-- 
    Document   : header
    Created on : Feb 1, 2022, 9:59:18 PM
    Author     : Vu Duc Tien
--%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <title>Quizz</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Favicon -->
        <link href="img/favicon.ico" rel="icon">

        <!-- Google Web Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600&family=Nunito:wght@600;700;800&display=swap" rel="stylesheet">

        <!-- Icon Font Stylesheet -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

        <!-- Libraries Stylesheet -->
        <link href="${pageContext.request.contextPath}/lib/animate/animate.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

        <!-- Customized Bootstrap Stylesheet -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css?v=5" rel="stylesheet">

        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/popup.css" rel="stylesheet">
        <link rel="preload" as="script" nonce="" href="https://cf.quizizz.com/assets/v3/j/scripts/js/app.3a57798ffbad956e375f.js">
        <link rel="preload" as="script" nonce="" href="https://cf.quizizz.com/assets/v3/j/scripts/js/app_components.b7806b3b0327a37c5aaa.js">
        <link rel="preload" as="script" nonce="" href="https://cf.quizizz.com/assets/v3/j/scripts/js/app_components.b7806b3b0327a37c5aaa.js">
        <link rel="preload" as="script" nonce="" href="https://cf.quizizz.com/assets/v3/j/scripts/js/app_config-networkCalls.a028946b21e771ace9aa.js">
        <link rel="preload" as="script" nonce="" href="https://cf.quizizz.com/assets/v3/j/scripts/js/app_containers.cc0613bfbdc96c82b8c2.js">
        <link rel="preload" as="script" nonce="" href="https://cf.quizizz.com/assets/v3/j/scripts/js/app_cookers.32cfae0110ad4ac3598b.js">
        <link rel="preload" as="script" nonce="" href="https://cf.quizizz.com/assets/v3/j/scripts/js/app_mostUnchanging.c74b7f7d444862222c04.js">
        <link rel="preload" as="script" nonce="" href="https://cf.quizizz.com/assets/v3/j/scripts/js/app_services.4977e47072f8956924cb.js">  
        <link rel="preload" as="script" nonce="" href="https://cf.quizizz.com/assets/v3/j/scripts/js/app_state.e0cb69541feb33a35a44.js"> 
        <link rel="preload" as="script" nonce="" href="https://cf.quizizz.com/assets/v3/j/scripts/js/app_utils.f9935893ea81fbbff958.js">    
        <link rel="preload" as="script" nonce="" href="https://cf.quizizz.com/assets/v3/j/scripts/js/common~route_game~route_landing~route_preGame.4a6db1d395453cbe5328.js">
        <link rel="preload" as="script" nonce="" href="https://cf.quizizz.com/assets/v3/j/scripts/js/route_landing.cf46a69c58bc58bcdf9a.js">
        <link rel="preload" as="script" nonce="" href="https://cf.quizizz.com/assets/v3/j/scripts/js/vendor_coreJs.6effde537a28360e9477.js">

        <style type="text/css">.activity-wrapper[data-v-0a48d845]{min-height:100vh;position:relative;margin:0 auto;max-width:1366px}@media screen and (min-width:576px){.activity-wrapper[data-v-0a48d845]{z-index:2;padding:0 0 50px 0;margin-top:58px}}.activity-container[data-v-0a48d845]{min-height:100vh}@media screen and (min-width:576px){.activity-container[data-v-0a48d845]{border-radius:16px}}.header-wrapper[data-v-0a48d845]{padding:4px 12px;color:#fff;display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-box-align:center;-webkit-align-items:center;-ms-flex-align:center;align-items:center;position:fixed;top:0;left:0;width:100%;z-index:2;background-color:#461a42}.avatar-wrapper[data-v-0a48d845]{position:relative;cursor:pointer}.avatar-wrapper img[data-v-0a48d845],.select-avatar[data-v-0a48d845]{border-radius:50%;width:32px;height:32px}.select-avatar[data-v-0a48d845]{background-color:#d8d8d8;text-align:center}.select-avatar-icon[data-v-0a48d845]{display:inline-block;margin-top:-8px;color:#fff;font-size:35px;font-weight:700;border-radius:50%}.profile-info[data-v-0a48d845]{margin-left:8px}.navigator-dummy-mask[data-v-0a48d845]{width:100%;position:fixed;top:0;left:0;background:#f2f2f2;height:105px;z-index:1}@media screen and (min-width:576px){.navigator-dummy-mask[data-v-0a48d845]{display:none}}.navigator[data-v-0a48d845]{background:#fff;display:-webkit-inline-box;display:-webkit-inline-flex;display:-ms-inline-flexbox;display:inline-flex;border-radius:16px;position:relative;font-size:14px;font-weight:700;-webkit-box-align:center;-webkit-align-items:center;-ms-flex-align:center;align-items:center}.switch[data-v-0a48d845]{position:absolute;width:50%;border-radius:16px;background-color:#efa929;height:calc(100% - 4px);left:2px;top:2px;-webkit-transition:left .15s cubic-bezier(.215,.61,.355,1);transition:left .15s cubic-bezier(.215,.61,.355,1)}.switch-right[data-v-0a48d845]{left:119px}.menu-item[data-v-0a48d845]{line-height:28px;padding:2px 26px;z-index:2;position:relative;color:#292a3a;-webkit-transition:color .15s cubic-bezier(.215,.61,.355,1);transition:color .15s cubic-bezier(.215,.61,.355,1)}.menu-item.active[data-v-0a48d845]{color:#fff}.menu-item[data-v-0a48d845]:first-child{left:4px}.in-page-navigator[data-v-0a48d845]{background-color:#fff;position:relative;left:0;right:0;top:0;z-index:5;-webkit-transition:-webkit-box-shadow .3s ease;transition:-webkit-box-shadow .3s ease;transition:box-shadow .3s ease;transition:box-shadow .3s ease,-webkit-box-shadow .3s ease}.in-page-navigator.sticky[data-v-0a48d845]{position:fixed;-webkit-box-shadow:0 2px 8px 0 rgba(0,0,0,.2);box-shadow:0 2px 8px 0 rgba(0,0,0,.2)}@media screen and (min-width:576px){.in-page-navigator[data-v-0a48d845]{position:fixed;top:55px}}.navigation-wrapper[data-v-0a48d845]{max-width:1366px;padding:0;margin:0 auto}.navigator-tabs[data-v-0a48d845]{padding:18px 16px 0;display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-box-align:center;-webkit-align-items:center;-ms-flex-align:center;align-items:center;-webkit-box-pack:center;-webkit-justify-content:center;-ms-flex-pack:center;justify-content:center;border-radius:0}@media screen and (min-width:576px){.navigator-tabs[data-v-0a48d845]{-webkit-box-pack:initial;-webkit-justify-content:initial;-ms-flex-pack:initial;justify-content:normal;padding:20px 20px 0}}.navigator-tabs>.tab[data-v-0a48d845]{display:-webkit-inline-box;display:-webkit-inline-flex;display:-ms-inline-flexbox;display:inline-flex;-webkit-box-pack:center;-webkit-justify-content:center;-ms-flex-pack:center;justify-content:center;white-space:nowrap;-webkit-box-flex:1;-webkit-flex:1;-ms-flex:1;flex:1;padding:8px;font-size:16px;font-weight:700;color:rgba(41,42,58,.34);cursor:pointer}.navigator-tabs>.tab .tab-name[data-v-0a48d845]{font-weight:700;margin-right:4px}.navigator-tabs>.tab.active[data-v-0a48d845]{color:#292a3a;border-bottom:4px solid #efa929}@media screen and (min-width:576px){.navigator-tabs>.tab[data-v-0a48d845]{padding:18px 16px;font-size:16px;-webkit-box-flex:initial;-webkit-flex:initial;-ms-flex:initial;flex:initial}.navigator-tabs>.tab .tab-name[data-v-0a48d845]{font-weight:500;margin-right:8px}}.all-games[data-v-0a48d845]{font-size:16px;font-weight:500;padding:16px 20px 90px 20px}.all-games.sticky[data-v-0a48d845]{padding:71px 20px 90px 20px}@media screen and (min-width:576px){.all-games[data-v-0a48d845]{padding-top:88px}}</style>
        <style type="text/css">.game-card-q-loader[data-v-4036a0ae]{position:absolute;width:100%;height:100%;display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-box-pack:center;-webkit-justify-content:center;-ms-flex-pack:center;justify-content:center;-webkit-box-align:center;-webkit-align-items:center;-ms-flex-align:center;align-items:center;-webkit-box-orient:vertical;-webkit-box-direction:normal;-webkit-flex-direction:column;-ms-flex-direction:column;flex-direction:column;background-color:rgba(0,0,0,.66);z-index:100}.loading-text[data-v-4036a0ae]{font-size:16px;color:#fff;font-weight:700;margin-top:8px}</style>
        <style type="text/css">.game-card-container[data-v-0a33e012]{width:100%;font-weight:500}.game-type-info[data-v-0a33e012]{position:absolute;top:8px;left:8px;font-size:12px;color:#fff;border-radius:4px;background-color:rgba(41,42,58,.9);padding:4px;display:-webkit-inline-box;display:-webkit-inline-flex;display:-ms-inline-flexbox;display:inline-flex;-webkit-box-align:center;-webkit-align-items:center;-ms-flex-align:center;align-items:center;-webkit-box-shadow:inset 0 0 0 1px rgba(0,0,0,.1);box-shadow:inset 0 0 0 1px rgba(0,0,0,.1)}@media screen and (min-width:576px){.game-type-info[data-v-0a33e012]{left:12px;padding:4px 8px}}.game-type-icon[data-v-0a33e012]{font-size:12px}.homework-icon[data-v-0a33e012]{color:#5de2a5}.media-wrapper[data-v-0a33e012]{width:100%!important;border-top-left-radius:8px;border-top-right-radius:8px;z-index:0;position:relative}.media[data-v-0a33e012]{background-repeat:no-repeat;background-size:cover;background-position:50%;background-color:#f2f2f2}.media-dimensions[data-v-0a33e012]{width:100%;height:64px;border-top-left-radius:8px;border-top-right-radius:8px}@media (min-width:576px){.media-dimensions[data-v-0a33e012]{height:80px}}.quiz-name[data-v-0a33e012]{font-size:14px;font-weight:500;margin:8px 8px 0 8px;text-align:left}@media (min-width:576px){.quiz-name[data-v-0a33e012]{font-size:18px;margin:0px 12px 0px 12px}}.quiz-name-underline[data-v-0a33e012]{display:none}@media (min-width:576px){.quiz-name-underline[data-v-0a33e012]{display:block;height:2px;width:48px;background:#8854c0;margin-left:12px;border-radius:50px}}.quiz-info[data-v-0a33e012]{position:absolute;top:47px;display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-box-pack:justify;-webkit-justify-content:space-between;-ms-flex-pack:justify;justify-content:space-between;width:100%;padding:0 8px;-webkit-box-sizing:border-box;box-sizing:border-box}@media screen and (min-width:576px){.quiz-info[data-v-0a33e012]{top:61px;padding:0 12px}}.questions-length[data-v-0a33e012],.times-played[data-v-0a33e012]{font-size:10px;padding:1px 6px;border-radius:4px}@media (min-width:576px){.questions-length[data-v-0a33e012],.times-played[data-v-0a33e012]{font-size:12px}}.questions-length[data-v-0a33e012]{color:#292a3a;background:#f2f2f2;-webkit-box-shadow:0 0 0 1px rgba(0,0,0,.1);box-shadow:0 0 0 1px rgba(0,0,0,.1);border:1px solid #e6e6e6}.times-played[data-v-0a33e012]{color:#fff;background:#8854c0;-webkit-box-shadow:0 0 0 1px #8854c0,0 0 0 1px rgba(0,0,0,.1);box-shadow:0 0 0 1px #8854c0,0 0 0 1px rgba(0,0,0,.1);border:1px solid #7a4bac}.game-progress-bar-background[data-v-0a33e012]{background-color:rgba(123,76,173,.3);height:20px;position:absolute;bottom:6px;left:8px;right:8px;width:calc(100% - 16px);border-radius:11px;display:-webkit-inline-box;display:-webkit-inline-flex;display:-ms-inline-flexbox;display:inline-flex;-webkit-box-align:center;-webkit-align-items:center;-ms-flex-align:center;align-items:center}.game-progress-bar-background.when-assigned[data-v-0a33e012]{background-color:transparent;border:1px dashed #9a4292}.game-progress-bar[data-v-0a33e012]{background-color:#7b4cad;position:absolute;left:0;bottom:0;height:20px;border-radius:11px;min-width:36px;-webkit-box-align:center;-webkit-align-items:center;-ms-flex-align:center;align-items:center}.game-progress-bar.when-assigned[data-v-0a33e012]{background:transparent}@media screen and (min-width:576px){.game-progress-bar[data-v-0a33e012]{min-width:42px}}.game-progress-bar-text[data-v-0a33e012]{font-size:10px;color:#fff;font-weight:500;padding-left:16px;text-shadow:0 2px 4px rgba(0,0,0,.1);position:relative;z-index:2}.game-progress-bar-text.when-assigned[data-v-0a33e012]{color:#9a4292}@media screen and (min-width:576px){.game-progress-bar-text[data-v-0a33e012]{font-size:12px}}.accuracy-bar[data-v-0a33e012]{height:20px;border-radius:10px;position:absolute;bottom:6px;right:8px;left:8px;width:calc(100% - 16px);display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-box-orient:vertical;-webkit-box-direction:normal;-webkit-flex-direction:column;-ms-flex-direction:column;flex-direction:column;-webkit-box-pack:center;-webkit-justify-content:center;-ms-flex-pack:center;justify-content:center}.accuracy-bar.perfect[data-v-0a33e012]{background-color:#292a3a}.accuracy-bar.perfect .accuracy-bar-text[data-v-0a33e012]{color:#efa929}.accuracy-bar.poor[data-v-0a33e012]{background-color:#f14d76}.accuracy-bar.average[data-v-0a33e012]{background-color:#f5a623}.accuracy-bar.great[data-v-0a33e012]{background-color:#5de2a5}@media screen and (min-width:576px){.accuracy-bar[data-v-0a33e012]{right:12px;left:12px;width:calc(100% - 24px)}}.accuracy-bar-text[data-v-0a33e012]{font-size:12px;padding-left:16px;color:#fff;font-weight:500}.game-details[data-v-0a33e012]{padding-top:4px}@media screen and (min-width:576px){.game-details[data-v-0a33e012]{padding-top:6px}}.assignment-info[data-v-0a33e012]{font-size:10px;font-weight:500;padding:0 8px 4px 8px;color:#292a3a}@media screen and (min-width:576px){.assignment-info[data-v-0a33e012]{padding:0 12px 4px 12px;font-size:12px}}.assigned-by-title[data-v-0a33e012]{color:rgba(41,42,58,.66);display:none}@media screen and (min-width:576px){.assigned-by-title[data-v-0a33e012]{display:initial}}.due-info[data-v-0a33e012]{font-size:10px;font-weight:500;color:#292a3a;padding:0 8px 0 8px}@media screen and (min-width:576px){.due-info[data-v-0a33e012]{padding:0 12px 0 12px;font-size:12px}}.urgent-due[data-v-0a33e012]{color:#ec0b43}.media-wrapper-dummy[data-v-0a33e012]{background-color:rgba(0,0,0,.1)}.quiz-name-dummy[data-v-0a33e012]{margin:10px 12px}.quiz-name-dummy[data-v-0a33e012],.quiz-sub-name-dummy[data-v-0a33e012]{height:16px;border-radius:4px;padding:0}.quiz-sub-name-dummy[data-v-0a33e012]{width:50%;margin-left:12px}.questions-length-dummy[data-v-0a33e012]{height:16px;width:39px;padding:0;display:-webkit-inline-box;display:-webkit-inline-flex;display:-ms-inline-flexbox;display:inline-flex;-webkit-box-align:center;-webkit-align-items:center;-ms-flex-align:center;align-items:center;-webkit-box-pack:center;-webkit-justify-content:center;-ms-flex-pack:center;justify-content:center}.questions-length-sub-dummy[data-v-0a33e012]{width:31px;height:8px;border-radius:2px;background-color:#e7e7e7}.notif-icon[data-v-0a33e012]{position:absolute;top:46px;right:12px;width:28px;height:28px;background-color:#ec0b43;color:#fff;font-size:14px;border-radius:50%}.notif-icon i[data-v-0a33e012]{-webkit-animation-iteration-count:15;animation-iteration-count:15;-webkit-animation-duration:2s;animation-duration:2s}@media screen and (min-width:576px){.notif-icon[data-v-0a33e012]{top:60px}}.shadow-pulse[data-v-0a33e012]{-webkit-box-shadow:0 0 0 rgba(236,11,67,.7);box-shadow:0 0 0 rgba(236,11,67,.7);-webkit-animation:shadowPulse-data-v-0a33e012 2s 15;animation:shadowPulse-data-v-0a33e012 2s 15;-webkit-animation-delay:.3s;animation-delay:.3s}.q-loader-container[data-v-0a33e012]{position:absolute;height:100%;width:100%;z-index:100;background-color:rgba(0,0,0,.1)}@-webkit-keyframes shadowPulse-data-v-0a33e012{0%{-webkit-box-shadow:0 0 0 0 rgba(236,11,67,.7);box-shadow:0 0 0 0 rgba(236,11,67,.7)}25%{-webkit-box-shadow:0 0 0 5px rgba(236,11,67,.5);box-shadow:0 0 0 5px rgba(236,11,67,.5)}70%{-webkit-box-shadow:0 0 0 10px rgba(236,11,67,0);box-shadow:0 0 0 10px rgba(236,11,67,0)}to{-webkit-box-shadow:0 0 0 0 rgba(236,11,67,0);box-shadow:0 0 0 0 rgba(236,11,67,0)}}@keyframes shadowPulse-data-v-0a33e012{0%{-webkit-box-shadow:0 0 0 0 rgba(236,11,67,.7);box-shadow:0 0 0 0 rgba(236,11,67,.7)}25%{-webkit-box-shadow:0 0 0 5px rgba(236,11,67,.5);box-shadow:0 0 0 5px rgba(236,11,67,.5)}70%{-webkit-box-shadow:0 0 0 10px rgba(236,11,67,0);box-shadow:0 0 0 10px rgba(236,11,67,0)}to{-webkit-box-shadow:0 0 0 0 rgba(236,11,67,0);box-shadow:0 0 0 0 rgba(236,11,67,0)}}</style>
        <style type="text/css">.games[data-v-8d3099dc]{display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;height:184px;-webkit-box-align:center;-webkit-align-items:center;-ms-flex-align:center;align-items:center}@media screen and (min-width:576px){.games[data-v-8d3099dc]{height:236px}}.game[data-v-8d3099dc]{width:160px;height:174px;position:relative;overflow:hidden;margin-right:8px;border-radius:8px;background-color:#fff;-webkit-box-shadow:0 0 0 1px rgba(0,0,0,.05),0 3px 6px 1px rgba(0,0,0,.05);box-shadow:0 0 0 1px rgba(0,0,0,.05),0 3px 6px 1px rgba(0,0,0,.05);-webkit-transition:-webkit-box-shadow .15s cubic-bezier(.165,.84,.44,1);transition:-webkit-box-shadow .15s cubic-bezier(.165,.84,.44,1);transition:box-shadow .15s cubic-bezier(.165,.84,.44,1);transition:box-shadow .15s cubic-bezier(.165,.84,.44,1),-webkit-box-shadow .15s cubic-bezier(.165,.84,.44,1);will-change:box-shadow;cursor:pointer}.game[data-v-8d3099dc]:first-child{margin-left:12px}.game[data-v-8d3099dc]:last-child{margin-right:12px}.game[data-v-8d3099dc]:hover{-webkit-box-shadow:0 0 0 1px rgba(0,0,0,.05),-4px 6px 8px 4px rgba(0,0,0,.05),4px 6px 8px 4px rgba(0,0,0,.05);box-shadow:0 0 0 1px rgba(0,0,0,.05),-4px 6px 8px 4px rgba(0,0,0,.05),4px 6px 8px 4px rgba(0,0,0,.05)}@media screen and (min-width:576px){.game[data-v-8d3099dc]{width:200px;height:216px;margin-right:20px}.game[data-v-8d3099dc]:first-child{margin-left:0}.game[data-v-8d3099dc]:last-child{margin-right:0}}.completed-games-list[data-v-8d3099dc],.running-games-list[data-v-8d3099dc]{-webkit-flex-wrap:wrap;-ms-flex-wrap:wrap;flex-wrap:wrap}.completed-game[data-v-8d3099dc],.completed-games-list[data-v-8d3099dc],.running-game[data-v-8d3099dc],.running-games-list[data-v-8d3099dc]{display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex}.completed-game[data-v-8d3099dc],.running-game[data-v-8d3099dc]{-webkit-box-orient:vertical;-webkit-box-direction:normal;-webkit-flex-direction:column;-ms-flex-direction:column;flex-direction:column;border:none;width:calc(50% - 6px);position:relative;height:174px;overflow:hidden;margin:0 12px 12px 0;border-radius:8px;background-color:#fff;-webkit-box-shadow:0 0 0 1px rgba(0,0,0,.05),0 3px 6px 1px rgba(0,0,0,.05);box-shadow:0 0 0 1px rgba(0,0,0,.05),0 3px 6px 1px rgba(0,0,0,.05);-webkit-transition:-webkit-box-shadow .15s cubic-bezier(.165,.84,.44,1);transition:-webkit-box-shadow .15s cubic-bezier(.165,.84,.44,1);transition:box-shadow .15s cubic-bezier(.165,.84,.44,1);transition:box-shadow .15s cubic-bezier(.165,.84,.44,1),-webkit-box-shadow .15s cubic-bezier(.165,.84,.44,1);will-change:box-shadow;cursor:pointer}.completed-game[data-v-8d3099dc]:hover,.running-game[data-v-8d3099dc]:hover{-webkit-box-shadow:0 0 0 1px rgba(0,0,0,.05),-4px 6px 8px 4px rgba(0,0,0,.05),4px 6px 8px 4px rgba(0,0,0,.05);box-shadow:0 0 0 1px rgba(0,0,0,.05),-4px 6px 8px 4px rgba(0,0,0,.05),4px 6px 8px 4px rgba(0,0,0,.05)}.completed-game[data-v-8d3099dc]:nth-child(2n),.running-game[data-v-8d3099dc]:nth-child(2n){margin-right:0}@media screen and (min-width:576px){.completed-game[data-v-8d3099dc],.running-game[data-v-8d3099dc]{height:280px;margin:0 16px 16px 0;width:calc(33% - 9px)}.completed-game[data-v-8d3099dc]:nth-child(2n),.running-game[data-v-8d3099dc]:nth-child(2n){margin-right:16px}.completed-game[data-v-8d3099dc]:nth-child(3n),.running-game[data-v-8d3099dc]:nth-child(3n){margin-right:0}}@media screen and (min-width:1025px){.completed-game[data-v-8d3099dc],.running-game[data-v-8d3099dc]{width:calc(25% - 13px);margin-bottom:16px}.completed-game[data-v-8d3099dc]:nth-child(2n),.completed-game[data-v-8d3099dc]:nth-child(3n),.running-game[data-v-8d3099dc]:nth-child(2n),.running-game[data-v-8d3099dc]:nth-child(3n){margin-right:16px}.completed-game[data-v-8d3099dc]:nth-child(4n),.running-game[data-v-8d3099dc]:nth-child(4n){margin-right:0}}@media screen and (min-width:1201px){.completed-game[data-v-8d3099dc],.running-game[data-v-8d3099dc]{width:calc(20% - 13px);margin-bottom:16px}.completed-game[data-v-8d3099dc]:nth-child(2n),.completed-game[data-v-8d3099dc]:nth-child(3n),.completed-game[data-v-8d3099dc]:nth-child(4n),.running-game[data-v-8d3099dc]:nth-child(2n),.running-game[data-v-8d3099dc]:nth-child(3n),.running-game[data-v-8d3099dc]:nth-child(4n){margin-right:16px}.completed-game[data-v-8d3099dc]:nth-child(5n),.running-game[data-v-8d3099dc]:nth-child(5n){margin-right:0}}.anim-200-duration[data-v-8d3099dc]{-webkit-animation-duration:.2s!important;animation-duration:.2s!important}</style>
        <style type="text/css">.solo-quizzes-container[data-v-6482f4a3]{background:#f2f2f2}.solo-quizzes[data-v-6482f4a3]{-webkit-flex-wrap:wrap;-ms-flex-wrap:wrap;flex-wrap:wrap;font-weight:500}.dummy-solo-quiz[data-v-6482f4a3],.solo-quiz[data-v-6482f4a3],.solo-quizzes[data-v-6482f4a3]{display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex}.dummy-solo-quiz[data-v-6482f4a3],.solo-quiz[data-v-6482f4a3]{-webkit-box-orient:vertical;-webkit-box-direction:normal;-webkit-flex-direction:column;-ms-flex-direction:column;flex-direction:column;width:calc(50% - 6px);position:relative;height:162px;overflow:hidden;margin:0 12px 12px 0;border:none;border-radius:8px;background-color:#fff;-webkit-box-shadow:0 0 0 1px rgba(0,0,0,.05),0 3px 6px 1px rgba(0,0,0,.05);box-shadow:0 0 0 1px rgba(0,0,0,.05),0 3px 6px 1px rgba(0,0,0,.05);cursor:pointer}.dummy-solo-quiz.max-in-row-2[data-v-6482f4a3]:nth-child(2n),.solo-quiz.max-in-row-2[data-v-6482f4a3]:nth-child(2n){margin-right:0}@media screen and (min-width:576px){.dummy-solo-quiz.max-in-row-3[data-v-6482f4a3],.solo-quiz.max-in-row-3[data-v-6482f4a3]{height:204px;margin:0 16px 16px 0;width:calc(33% - 9px)}.dummy-solo-quiz.max-in-row-3[data-v-6482f4a3]:nth-child(2n),.solo-quiz.max-in-row-3[data-v-6482f4a3]:nth-child(2n){margin-right:16px}.dummy-solo-quiz.max-in-row-3[data-v-6482f4a3]:nth-child(3n),.solo-quiz.max-in-row-3[data-v-6482f4a3]:nth-child(3n){margin-right:0}}@media screen and (min-width:1025px){.dummy-solo-quiz.max-in-row-4[data-v-6482f4a3],.solo-quiz.max-in-row-4[data-v-6482f4a3]{width:calc(25% - 13px);margin-bottom:16px}.dummy-solo-quiz.max-in-row-4[data-v-6482f4a3]:nth-child(2n),.dummy-solo-quiz.max-in-row-4[data-v-6482f4a3]:nth-child(3n),.solo-quiz.max-in-row-4[data-v-6482f4a3]:nth-child(2n),.solo-quiz.max-in-row-4[data-v-6482f4a3]:nth-child(3n){margin-right:16px}.dummy-solo-quiz.max-in-row-4[data-v-6482f4a3]:nth-child(4n),.solo-quiz.max-in-row-4[data-v-6482f4a3]:nth-child(4n){margin-right:0}}@media screen and (min-width:1201px){.dummy-solo-quiz.max-in-row-5[data-v-6482f4a3],.solo-quiz.max-in-row-5[data-v-6482f4a3]{width:calc(20% - 13px);margin-bottom:16px}.dummy-solo-quiz.max-in-row-5[data-v-6482f4a3]:nth-child(2n),.dummy-solo-quiz.max-in-row-5[data-v-6482f4a3]:nth-child(3n),.dummy-solo-quiz.max-in-row-5[data-v-6482f4a3]:nth-child(4n),.solo-quiz.max-in-row-5[data-v-6482f4a3]:nth-child(2n),.solo-quiz.max-in-row-5[data-v-6482f4a3]:nth-child(3n),.solo-quiz.max-in-row-5[data-v-6482f4a3]:nth-child(4n){margin-right:16px}.dummy-solo-quiz.max-in-row-5[data-v-6482f4a3]:nth-child(5n),.solo-quiz.max-in-row-5[data-v-6482f4a3]:nth-child(5n){margin-right:0}}.solo-quiz[data-v-6482f4a3]{-webkit-transition:-webkit-box-shadow .15s cubic-bezier(.165,.84,.44,1);transition:-webkit-box-shadow .15s cubic-bezier(.165,.84,.44,1);transition:box-shadow .15s cubic-bezier(.165,.84,.44,1);transition:box-shadow .15s cubic-bezier(.165,.84,.44,1),-webkit-box-shadow .15s cubic-bezier(.165,.84,.44,1);will-change:box-shadow;border:none}.solo-quiz[data-v-6482f4a3]:hover{-webkit-box-shadow:0 0 0 1px rgba(0,0,0,.05),-4px 6px 8px 4px rgba(0,0,0,.05),4px 6px 8px 4px rgba(0,0,0,.05);box-shadow:0 0 0 1px rgba(0,0,0,.05),-4px 6px 8px 4px rgba(0,0,0,.05),4px 6px 8px 4px rgba(0,0,0,.05)}.media-wrapper[data-v-6482f4a3]{width:100%!important;border-top-left-radius:8px;border-top-right-radius:8px;z-index:0;position:relative}.media[data-v-6482f4a3]{background-color:#f2f2f2;background-repeat:no-repeat;background-size:cover;background-position:50%}.media-dimensions-full-width[data-v-6482f4a3]{width:100%}.media-dimensions[data-v-6482f4a3]{width:100%;height:100px}@media (min-width:576px){.media-dimensions[data-v-6482f4a3]{height:120px}}.quiz-name[data-v-6482f4a3]{font-size:14px;font-weight:500;margin:12px 12px 0 12px;text-align:left}@media (min-width:576px){.quiz-name[data-v-6482f4a3]{font-size:18px;margin:12px 12px 0 12px}}.quiz-info[data-v-6482f4a3]{position:absolute;top:85px;display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-box-pack:justify;-webkit-justify-content:space-between;-ms-flex-pack:justify;justify-content:space-between;width:100%}@media (min-width:576px){.quiz-info[data-v-6482f4a3]{top:102px}}.questions-length[data-v-6482f4a3],.times-played[data-v-6482f4a3]{font-size:10px;padding:1px 6px;border-radius:4px;background:#f2f2f2;-webkit-box-shadow:0 0 0 1px rgba(0,0,0,.1);box-shadow:0 0 0 1px rgba(0,0,0,.1)}@media (min-width:576px){.questions-length[data-v-6482f4a3],.times-played[data-v-6482f4a3]{font-size:12px}}.questions-length[data-v-6482f4a3]{color:rgba(41,42,58,.66);margin-left:10px}.times-played[data-v-6482f4a3]{color:#8854c0;margin-right:10px}.media-dimensions-dummy[data-v-6482f4a3]{background-color:#f2f2f2}.quiz-name-dummy[data-v-6482f4a3]{height:8px;border-radius:4px;margin:10px 12px;padding:0;background:#f2f2f2}@media screen and (min-width:576px){.quiz-name-dummy[data-v-6482f4a3]{height:16px}}.quiz-sub-name-dummy[data-v-6482f4a3]{height:8px;border-radius:4px;width:50%;padding:0;margin-left:12px;background:#f2f2f2}@media screen and (min-width:576px){.quiz-sub-name-dummy[data-v-6482f4a3]{height:16px}}.questions-length-dummy[data-v-6482f4a3]{height:16px;width:39px;padding:0;display:-webkit-inline-box;display:-webkit-inline-flex;display:-ms-inline-flexbox;display:inline-flex;-webkit-box-align:center;-webkit-align-items:center;-ms-flex-align:center;align-items:center;-webkit-box-pack:center;-webkit-justify-content:center;-ms-flex-pack:center;justify-content:center;background:#f2f2f2}.questions-length-sub-dummy[data-v-6482f4a3]{width:31px;height:8px;border-radius:2px;background-color:#e7e7e7}</style>
        <style type="text/css">.games-errored[data-v-b3f550ea]{background:#fff;border-radius:8px;padding:24px}@media screen and (min-width:576px){.games-errored[data-v-b3f550ea]{padding:50px 40px}}.games-try-again[data-v-b3f550ea]{margin-top:24px;height:36px!important;background-color:rgba(239,169,41,.2)!important}.games-error-message[data-v-b3f550ea]{font-size:16px;color:#292a3a;font-weight:500}</style>
        <style type="text/css">.activity-sub-route-container[data-v-3f400e64]{width:100%}.completed-games[data-v-3f400e64],.running-games[data-v-3f400e64]{min-height:170px}@media screen and (min-width:576px){.completed-games[data-v-3f400e64],.running-games[data-v-3f400e64]{min-height:224px}}.my-quizzes-wrapper[data-v-3f400e64],.running-games-wrapper[data-v-3f400e64]{margin-bottom:24px}.completed-games-text[data-v-3f400e64],.my-quizzes-text[data-v-3f400e64],.running-games-text[data-v-3f400e64]{margin-bottom:8px;font-weight:500;font-size:14px;color:rgba(41,42,58,.67)}.completed-games-list[data-v-3f400e64],.my-quizzes-list[data-v-3f400e64],.running-games-list[data-v-3f400e64]{display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-flex-wrap:wrap;-ms-flex-wrap:wrap;flex-wrap:wrap}.completed-game[data-v-3f400e64],.quiz-item[data-v-3f400e64],.running-game[data-v-3f400e64]{width:calc(50% - 4px);position:relative;height:162px;overflow:hidden;margin:0 8px 8px 0;border-radius:8px;background-color:#fff;-webkit-box-shadow:0 4px 4px 2px rgba(0,0,0,.05),0 0 0 1px rgba(0,0,0,.05);box-shadow:0 4px 4px 2px rgba(0,0,0,.05),0 0 0 1px rgba(0,0,0,.05);cursor:pointer}.completed-game[data-v-3f400e64]:nth-child(2n),.quiz-item[data-v-3f400e64]:nth-child(2n),.running-game[data-v-3f400e64]:nth-child(2n){margin-right:0}@media screen and (min-width:576px){.completed-game[data-v-3f400e64],.quiz-item[data-v-3f400e64],.running-game[data-v-3f400e64]{height:204px;margin:0 18px 20px 0;width:calc(33% - 11px)}.completed-game[data-v-3f400e64]:nth-child(2n),.quiz-item[data-v-3f400e64]:nth-child(2n),.running-game[data-v-3f400e64]:nth-child(2n){margin-right:18px}.completed-game[data-v-3f400e64]:nth-child(3n),.quiz-item[data-v-3f400e64]:nth-child(3n),.running-game[data-v-3f400e64]:nth-child(3n){margin-right:0}}@media screen and (min-width:1025px){.completed-game[data-v-3f400e64],.quiz-item[data-v-3f400e64],.running-game[data-v-3f400e64]{width:calc(25% - 14px)}.completed-game[data-v-3f400e64]:nth-child(2n),.completed-game[data-v-3f400e64]:nth-child(3n),.quiz-item[data-v-3f400e64]:nth-child(2n),.quiz-item[data-v-3f400e64]:nth-child(3n),.running-game[data-v-3f400e64]:nth-child(2n),.running-game[data-v-3f400e64]:nth-child(3n){margin-right:18px}.completed-game[data-v-3f400e64]:nth-child(4n),.quiz-item[data-v-3f400e64]:nth-child(4n),.running-game[data-v-3f400e64]:nth-child(4n){margin-right:0}}.games-empty[data-v-3f400e64],.games-errored[data-v-3f400e64]{height:170px;border-radius:8px}@media screen and (min-width:576px){.games-empty[data-v-3f400e64],.games-errored[data-v-3f400e64]{height:224px}}.load-more-container[data-v-3f400e64]{display:-webkit-box;display:-webkit-flex;display:-ms-flexbox;display:flex;-webkit-box-pack:center;-webkit-justify-content:center;-ms-flex-pack:center;justify-content:center;margin-top:20px}.load-more-button[data-v-3f400e64]{color:#8854c0!important;background-color:#ede6f6!important}.no-results-create-suggestion[data-v-3f400e64]{margin-top:24px}.create-illustration[data-v-3f400e64]{width:160px;margin:0 auto 16px}.create-illustration img[data-v-3f400e64]{width:100%}.create-suggestion-text[data-v-3f400e64]{text-align:center;font-weight:500}.create-quiz-btn-container[data-v-3f400e64]{text-align:center;margin-top:12px}.create-quiz-btn[data-v-3f400e64]{width:155px;font-size:16px!important;font-weight:700!important;color:#8854c0!important;height:36px!important;border-radius:8px!important;-webkit-box-shadow:0 4px 0 0 #e7e7e7,0 4px 0 0 rgba(0,0,0,.09)!important;box-shadow:0 4px 0 0 #e7e7e7,0 4px 0 0 rgba(0,0,0,.09)!important;border:1px solid #e7e7e7!important;background-color:#fff!important;cursor:pointer}.anim-250-dur[data-v-3f400e64]{-webkit-animation-duration:.25s!important;animation-duration:.25s!important}.explore-more-button[data-v-3f400e64]{font-size:16px!important;font-weight:700!important;color:#8854c0!important;height:36px!important;border-radius:8px!important;-webkit-box-shadow:0 4px 0 0 #e7e7e7,0 4px 0 0 rgba(0,0,0,.09)!important;box-shadow:0 4px 0 0 #e7e7e7,0 4px 0 0 rgba(0,0,0,.09)!important;border:1px solid #e7e7e7!important;background-color:#fff!important}.empty-state-container[data-v-3f400e64]{text-align:center;padding-top:40px}.empty-state-avatar[data-v-3f400e64]{margin:0 auto 16px auto}.empty-state-avatar[data-v-3f400e64],.empty-state-avatar img[data-v-3f400e64]{width:225px;height:200px}@media screen and (max-width:575px){.empty-state-avatar[data-v-3f400e64],.empty-state-avatar img[data-v-3f400e64]{width:148px;height:auto}}.empty-state-message[data-v-3f400e64]{font-size:24px;color:#292a3a;margin-bottom:16px}@media screen and (max-width:575px){.empty-state-message[data-v-3f400e64]{font-size:20px;margin-bottom:12px}}</style>





    </head>

    <body>
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->
        <!-- Navbar Start -->
        <nav class="navbar navbar-expand-lg bg-white navbar-light shadow sticky-top p-0">
            <a href="${pageContext.request.contextPath}/home" class="navbar-brand d-flex align-items-center px-4 px-lg-5">
                <h2 class="m-0 text-primary"><i class="fa fa-book me-3"></i>Quizz</h2>
            </a>
            <button type="button" class="navbar-toggler me-4" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <div class="navbar-nav ms-auto p-4 p-lg-0">
                    <a href="${pageContext.request.contextPath}/home"  class="nav-item nav-link <c:if test="${requestScope.tag eq 'home'}">active</c:if>">Home</a>
                        <a href="about.html" class="nav-item nav-link">About</a>
                        <a href="${pageContext.request.contextPath}/post/list" class="nav-item nav-link">Posts</a>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                        <div class="dropdown-menu fade-down m-0">
                            <%--<c:if test="${sessionScope.account == null}">--%>
                                <!--<a href="${pageContext.request.contextPath}/practice/details" class="dropdown-item <c:if test="${requestScope.tag eq 'details'}">active</c:if>">Quizses</a>-->
                            <%--</c:if>--%>
                            <c:if test="${sessionScope.account != null}">
                                <a href="${pageContext.request.contextPath}/practice/list"class="dropdown-item <c:if test="${requestScope.tag eq 'list'}">active</c:if>">Practice List</a>
                            </c:if>
                            <a href="testimonial.html" class="dropdown-item">Testimonial</a>
                            <a href="404.html" class="dropdown-item">404 Page</a>
                        </div>
                    </div>
                    <a href="contact.html" class="nav-item nav-link">Contact</a>
                    <!--<a href="${pageContext.request.contextPath}/quiz/listquiz" class="nav-item nav-link">List Quizzes</a>-->
                    <c:forEach items="${sessionScope.account.role}" var="r">
                        <c:if test="${r.roleName == 'Admin'}">
                            <a href="${pageContext.request.contextPath}/profile" class="nav-item nav-link">List User</a>
                        </c:if>
                    </c:forEach>
                    <c:forEach items="${sessionScope.account.role}" var="r">
                        <c:if test="${r.roleName == 'Admin' || r.roleName == 'Author'}">
                            <a href="${pageContext.request.contextPath}/director" class="nav-item nav-link">For Director</a>
                        </c:if>
                    </c:forEach>
                    <c:if test="${sessionScope.account == null}"> 
                        <a href="${pageContext.request.contextPath}/login" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block">Join Now<i class="fa fa-arrow-right ms-3"></i></a>
                        </c:if>
                        <c:if test="${sessionScope.account != null}">             
                        <div class="inf button nav-item nav-link" style="padding: 18px 0 0 0;"><img src="https://th.bing.com/th/id/OIP.CPCyt52dUDhWIhy1K3guLAHaHa?pid=ImgDet&rs=1"
                                                                                                    style="width: 35px; height: 35px ; margin-right: 4px; border-radius: 50%;">
                            <a href="#popup1" style="    padding-top: 7px;">${sessionScope.account.fullname}</a>
                        </div>

                        <a style=" border-top: none;" class="nav-item nav-link" href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out-alt"></i>Logout</a>
                    </c:if>
                </div>
                <div id="popup1" class="overlay">
                    <div class="popup">
                        <form>
                            <h2 style="border-bottom: 1px solid black;">Information</h2>
                            <a class="close" href="#">&times;</a>
                            <div class="flex">
                                <div class="content">
                                    <ul style="font-weight: 600; padding-left: 0rem;">
                                        <li>ID: ${sessionScope.account.id}</li>
                                        <li>Username: ${sessionScope.account.username}</li>
                                        <li>Full name: <input name="fullname" type="text" value="${sessionScope.account.fullname}"/></li>
                                        <li>Gender: <c:if test="${sessionScope.account.gender == true}">Male</c:if>
                                            <c:if test="${sessionScope.account.gender == false}">Female</c:if></li>
                                        <li>Email:  ${sessionScope.account.email}</li>
                                        <li>Phone number: ${sessionScope.account.phone}</li>
                                        <li>Address: <input name="address" type="text" value="${sessionScope.account.address}"/></li>

                                    </ul>
                                    <a href="changepassword" class="btn btn-primary">Reset password</a>
                                </div>
                                <div class="img">
                                    <!--<img src="https://th.bing.com/th/id/OIP.CPCyt52dUDhWIhy1K3guLAHaHa?pid=ImgDet&rs=1">-->
                                    <img src="${sessionScope.account.avatar}"> 
                                </div>
                            </div>
                    </div>
                    <a href="contact.html" class="nav-item nav-link">Contact</a>
                    <!--<a href="${pageContext.request.contextPath}/quiz/listquiz" class="nav-item nav-link">List Quizzes</a>-->
                    <c:forEach items="${sessionScope.account.role}" var="r">
                        <c:if test="${r.roleName == 'Admin'}">
                            <a href="${pageContext.request.contextPath}/profile" class="nav-item nav-link">List User</a>
                        </c:if>
                    </c:forEach>
                    <c:forEach items="${sessionScope.account.role}" var="r">
                        <c:if test="${r.roleName == 'Admin'}">
                            <a href="${pageContext.request.contextPath}/director/setting/settinglist" class="nav-item nav-link">For Director</a>
                        </c:if>
                    </c:forEach>
                    <c:if test="${sessionScope.account == null}"> 
                        <a href="${pageContext.request.contextPath}/login" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block">Join Now<i class="fa fa-arrow-right ms-3"></i></a>
                        </c:if>
                        <c:if test="${sessionScope.account != null}">             
                        <div class="inf button nav-item nav-link" style="padding: 18px 0 0 0;"><img src="https://th.bing.com/th/id/OIP.CPCyt52dUDhWIhy1K3guLAHaHa?pid=ImgDet&rs=1"
                                                                                                    style="width: 35px; height: 35px ; margin-right: 4px; border-radius: 50%;">
                            <a href="#popup1" style="    padding-top: 7px;">${sessionScope.account.fullname}</a>
                        </div>

                        <a style=" border-top: none;" class="nav-item nav-link" href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out-alt"></i>Logout</a>
                    </c:if>
                </div>
                <div id="popup1" class="overlay">
                    <div class="popup">
                        <form>
                            <h2 style="border-bottom: 1px solid black;">Information</h2>
                            <a class="close" href="#">&times;</a>
                            <div class="flex">
                                <div class="content">
                                    <ul style="font-weight: 600; padding-left: 0rem;">
                                        <li>ID: ${sessionScope.account.id}</li>
                                        <li>Username: ${sessionScope.account.username}</li>
                                        <li>Full name: <input name="fullname" type="text" value="${sessionScope.account.fullname}"/></li>
                                        <li>Gender: <c:if test="${sessionScope.account.gender == true}">Male</c:if>
                                            <c:if test="${sessionScope.account.gender == false}">Female</c:if></li>
                                        <li>Email:  ${sessionScope.account.email}</li>
                                        <li>Phone number: ${sessionScope.account.phone}</li>
                                        <li>Address: <input name="address" type="text" value="${sessionScope.account.address}"/></li>

                                    </ul>
                                    <a href="changepassword" class="btn btn-primary">Reset password</a>
                                </div>
                                <div class="img">
                                    <!--<img src="https://th.bing.com/th/id/OIP.CPCyt52dUDhWIhy1K3guLAHaHa?pid=ImgDet&rs=1">-->
                                    <img src="${sessionScope.account.avatar}"> 
                                </div>
                            </div>
                    </div>
                    <a href="contact.html" class="nav-item nav-link">Contact</a>
                    <c:forEach items="${sessionScope.account.role}" var="r">
                        <c:if test="${r.roleName == 'Admin'}">
                            <a href="${pageContext.request.contextPath}/profile" class="nav-item nav-link">List User</a>
                        </c:if>
                    </c:forEach>
                    <c:forEach items="${sessionScope.account.role}" var="r">
                        <c:if test="${r.roleName == 'Admin'}">
                            <a href="${pageContext.request.contextPath}/director/setting/settinglist" class="nav-item nav-link">For Director</a>
                        </c:if>
                    </c:forEach>
                    <c:if test="${sessionScope.account == null}"> 
                        <a href="${pageContext.request.contextPath}/login" class="btn btn-primary py-4 px-lg-5 d-none d-lg-block">Join Now<i class="fa fa-arrow-right ms-3"></i></a>
                        </c:if>
                        <c:if test="${sessionScope.account != null}">             
                        <div class="inf button nav-item nav-link" style="padding: 18px 0 0 0;"><img src="https://th.bing.com/th/id/OIP.CPCyt52dUDhWIhy1K3guLAHaHa?pid=ImgDet&rs=1"
                                                                                                    style="width: 35px; height: 35px ; margin-right: 4px; border-radius: 50%;">
                            <a href="#popup1" style="    padding-top: 7px;">${sessionScope.account.fullname}</a>
                        </div>

                        <a style=" border-top: none;" class="nav-item nav-link" href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out-alt"></i>Logout</a>
                    </c:if>
                </div>
                <div id="popup1" class="overlay">
                    <div class="popup">
                        <form>
                            <h2 style="border-bottom: 1px solid black;">Information</h2>
                            <a class="close" href="#">&times;</a>
                            <div class="flex">
                                <div class="content">
                                    <ul style="font-weight: 600; padding-left: 0rem;">
                                        <li>ID: ${sessionScope.account.id}</li>
                                        <li>Username: ${sessionScope.account.username}</li>
                                        <li>Full name: <input name="fullname" type="text" value="${sessionScope.account.fullname}"/></li>
                                        <li>Gender: <c:if test="${sessionScope.account.gender == true}">Male</c:if>
                                            <c:if test="${sessionScope.account.gender == false}">Female</c:if></li>
                                        <li>Email:  ${sessionScope.account.email}</li>
                                        <li>Phone number: ${sessionScope.account.phone}</li>
                                        <li>Address: <input name="address" type="text" value="${sessionScope.account.address}"/></li>

                                    </ul>
                                    <a href="changepassword" class="btn btn-primary">Reset password</a>
                                </div>
                                <div class="img">
                                    <!--<img src="https://th.bing.com/th/id/OIP.CPCyt52dUDhWIhy1K3guLAHaHa?pid=ImgDet&rs=1">-->
                                    <img src="${sessionScope.account.avatar}"> 
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </nav>
    </header>
