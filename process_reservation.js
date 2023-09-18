document.addEventListener("DOMContentLoaded", function () {
    const submitBtn = document.getElementById("submitBtn");
    submitBtn.addEventListener("click", saveReservationData);
});

function saveReservationData() {
    const Name = document.getElementById("name").value;
    const Year = document.getElementById("year").value;
    const Branch = document.getElementById("branch").value;
    const College = document.getElementById("college").value;

    const xmlString = `
        <details>
            <fullName >${Name}</fullName>
            <year >${Year}</year>
            <branch  >${Branch}</branch>
            <college >${College}</college>
        </details>
    `;

    const blob = new Blob([xmlString], { type: "application/xml" });
    const link = document.createElement("a");
    link.href = URL.createObjectURL(blob);
    link.download = "details.xml";
    link.click();
}