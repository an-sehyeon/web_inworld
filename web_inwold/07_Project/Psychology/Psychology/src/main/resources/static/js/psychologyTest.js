const reverseScored = [4, 12, 16, 2, 5, 13, 17];  // 역순 점수 적용할 문항 리스트

/**
 * 테스트 결과 저장 및 차트 출력
 * @param {string} testType - 테스트 유형 (예: "도전성", "자기 평가")
 */
function saveTestResult(testType) {
    let responses = {};
    let totalScore = 0;
    let questionCount = testType === "자기 평가" ? 28 : 30;  // 테스트 유형에 따라 문항 개수 설정

    for (let i = 1; i <= questionCount; i++) {
        let selected = document.querySelector(`input[name="q${i}"]:checked`);
        if (selected) {
            let value = parseInt(selected.value);

            // 자기 평가 테스트의 경우 역순 점수 변환 적용
            if (testType === "자기 평가" && reverseScored.includes(i)) {
                value = 6 - value;
            }

            responses[`q${i}`] = value;
            totalScore += value;
        }
    }

    console.log("전송할 데이터:", { testName: testType, testResult: responses, totalScore });

    fetch("/psychologyTest/api/save", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            testName: testType,
            testResult: JSON.stringify(responses),
            totalScore: totalScore
        })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("서버 응답 실패");
        }
        return response.text();
    })
    .then(data => {
        alert("결과가 저장되었습니다!");  // 성공 알림창 표시
        console.log("서버 응답:", data);
        fetchTestResults(testType);  // 저장 후 차트 업데이트
    })
    .catch(error => {
        console.error("오류 발생:", error);
        alert("결과 저장에 실패했습니다.");
    });
}

/**
 * 테스트 결과를 가져와서 차트로 출력
 * @param {string} testType - 테스트 유형 (예: "도전성", "자기 평가")
 */
function fetchTestResults(testType) {
    fetch("/psychologyTest/api/results/1")  // TODO: 실제 userId로 변경
    .then(response => response.json())
    .then(data => {
        console.log("불러온 데이터:", data);
        if (data.length > 0) {
            let latestResult = data[data.length - 1]; // 가장 최근 결과 가져오기
            drawChart(testType, latestResult);
        }
    })
    .catch(error => console.error("데이터 불러오기 실패:", error));
}

/**
 * 차트 생성 함수 (Radar Chart)
 * @param {string} testType - 테스트 유형
 * @param {object} resultData - 저장된 테스트 결과 데이터
 */
function drawChart(testType, resultData) {
    let ctx = document.getElementById("resultChart").getContext("2d");

    let parsedData = JSON.parse(resultData.testResult);
    let labels = Object.keys(parsedData);
    let scores = Object.values(parsedData);

    new Chart(ctx, {
        type: "radar",
        data: {
            labels: labels,
            datasets: [{
                label: testType,
                data: scores,
                backgroundColor: "rgba(54, 162, 235, 0.2)",
                borderColor: "rgba(54, 162, 235, 1)",
                borderWidth: 1
            }]
        },
        options: { responsive: true }
    });
}
