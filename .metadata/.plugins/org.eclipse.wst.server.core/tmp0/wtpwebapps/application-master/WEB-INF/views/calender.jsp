<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<html>
<head>
    <title>가계부 캘린더</title>
    <link href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/jquery.min.js"></script>
</head>
<body>
<h1>📅 가계부 캘린더</h1>

<!-- 캘린더 표시 -->
<div id='calendar'></div>

<!-- 선택한 날짜의 기록을 표시할 영역 -->
<h2>📅 선택한 날짜의 기록</h2>
<div id="recordList">
    <c:if test="${empty records}">
        <p>기록이 없습니다.</p>
    </c:if>
    <c:forEach var="record" items="${records}">
        <p>
            📅 <fmt:formatDate value="${record.date}" pattern="yyyy-MM-dd" /> |
            💰 <fmt:formatNumber value="${record.amount}" pattern="#,###" />원 |
            🏷️ ${record.category} |
            📝 ${record.description} |
            🚩 ${record.type}
            <button onclick="editRecord(${record.id}, '${record.description}')">수정</button>
            <button onclick="deleteRecord(${record.id})">삭제</button>
        </p>
    </c:forEach>
</div>

<!-- 기록 추가 폼 -->
<h2>✏️ 새 기록 추가</h2>
<form id="addRecordForm">
    날짜: <input type="date" name="date" required><br>
    카테고리: <input type="text" name="category" required><br>
    내용: <input type="text" name="description"><br>
    금액: <input type="number" name="amount" required><br>
    유형: 
    <select name="type">
        <option value="income">수입</option>
        <option value="expense">지출</option>
    </select><br>
    <button type="submit">추가</button>
</form>

<script>
// FullCalendar 초기화
document.addEventListener('DOMContentLoaded', function() {
    let calendarEl = document.getElementById('calendar');
    let calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'dayGridMonth',
        events: '/api/records',  // DB에서 가져온 일정
        dateClick: function(info) {
            loadRecordsByDate(info.dateStr);  // 날짜별 기록 불러오기
        }
    });
    calendar.render();

    // 날짜별 기록 조회
    function loadRecordsByDate(date) {
        $.ajax({
            url: "/api/records/" + date,
            method: "GET",
            success: function(records) {
                let html = `<h3>${date}의 기록</h3><ul>`;
                if (records.length === 0) {
                    html += "<li>기록이 없습니다.</li>";
                } else {
                    records.forEach(record => {
                        html += `
                            <li>
                                ${record.category} - ${record.amount}원 (${record.type})
                                - ${record.description}
                                <button onclick="editRecord(${record.id}, '${record.description}')">수정</button>
                                <button onclick="deleteRecord(${record.id})">삭제</button>
                            </li>`;
                    });
                }
                html += "</ul>";
                $("#recordList").html(html);
            }
        });
    }

    // 기록 추가
    $("#addRecordForm").submit(function(event) {
        event.preventDefault();
        let formData = $(this).serializeArray();
        let data = {};
        formData.forEach(obj => data[obj.name] = obj.value);

        $.ajax({
            url: "/api/records",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(data),
            success: function() {
                alert("기록이 추가되었습니다.");
                calendar.refetchEvents();  // 이벤트 새로고침
            }
        });
    });

    // 기록 수정
    window.editRecord = function(id, currentDesc) {
        let newDesc = prompt("새로운 설명을 입력하세요:", currentDesc);
        if (newDesc) {
            $.ajax({
                url: "/api/records/" + id,
                method: "PUT",
                contentType: "application/json",
                data: JSON.stringify({ description: newDesc }),
                success: function() {
                    alert("기록이 수정되었습니다.");
                    calendar.refetchEvents();
                }
            });
        }
    };

    // 기록 삭제
    window.deleteRecord = function(id) {
        if (confirm("정말 삭제하시겠습니까?")) {
            $.ajax({
                url: "/api/records/" + id,
                method: "DELETE",
                success: function() {
                    alert("기록이 삭제되었습니다.");
                    calendar.refetchEvents();
                }
            });
        }
    };
});
</script>
</body>
</html>
