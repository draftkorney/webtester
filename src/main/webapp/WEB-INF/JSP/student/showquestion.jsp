<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c2" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--@elvariable id="questionForAnswer" type="ua.alex.source.webtester.forms.QuestionData"--%>
<c:set var="width" value="${(questionForAnswer.currentTime*100)/questionForAnswer.time}"/>

<div>

    <div class="progress">
        <div class="progress-bar" role="progressbar" aria-valuemin="0" aria-valuemax="100"
             style="width: ${width}%"></div>
    </div>

    <div><h3>${questionForAnswer.questionName}</h3></div>

    <form:form method="post" action="/answered" commandName="questionForAnswer">
        <form:hidden path="idQuestion"/>
        <div id="ul_answers">
            <c:forEach items="${questionForAnswer.answerList}" var="answer">

                <div class="checkbox">
                    <label>
                        <form:checkbox path="answerList" value="${answer.idAnswer}"/>
                            ${answer.name}
                    </label>
                </div>

            </c:forEach>
        </div>
        <button type="submit" class="btn btn-success btn-next" id="next" disabled>Next</button>
    </form:form>
</div>
<script>
    $(document).ready(function () {
        var time = ${questionForAnswer.time};
        var currentTime = ${questionForAnswer.currentTime};
        var width = ${width};
        var diffWidth = 100 / time;

        var timeBar = $('.progress-bar');
        timeBar.css('width', width + '%').attr('aria-valuenow', currentTime).attr('aria-valuemax', time);

        var form = $("form");
        var nextButton = $("button.btn-next");

        var ul_answers = $("#ul_answers");

        ul_answers.on('click', 'div.checkbox', function (e) {
            var checkedBoxes = ul_answers.find(':checkbox:checked').length;
            nextButton.prop("disabled", !(checkedBoxes > 0));
        });

        var timeout = setInterval(function () {
            if (currentTime <= 0) {
                $.removeCookie("time");
                form.submit();
            }
            currentTime -= 1;
            width -= diffWidth;
            $.cookie("time", currentTime, {expires: 0.00003});
            timeBar.css('width', width + '%').attr('aria-valuenow', currentTime);
        }, 1000);
    });


</script>

