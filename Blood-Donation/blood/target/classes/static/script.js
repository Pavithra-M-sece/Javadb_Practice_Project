const API_BASE = "http://localhost:8080/api";

// ========== DONORS ==========
async function fetchDonors() {
    const res = await fetch(`${API_BASE}/donors`);
    const donors = await res.json();
    const tbody = document.getElementById("donorTableBody");
    tbody.innerHTML = "";
    donors.forEach(d => {
        tbody.innerHTML += `
        <tr>
            <td>${d.id}</td>
            <td>${d.name}</td>
            <td>${d.bloodType}</td>
            <td><button class="btn btn-danger" onclick="deleteDonor(${d.id})">Delete</button></td>
        </tr>`;
    });
}

async function addDonor(event) {
    event.preventDefault();
    const donor = {
        name: document.getElementById("donorName").value,
        bloodType: document.getElementById("donorBloodType").value
    };
    await fetch(`${API_BASE}/donors`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(donor)
    });
    event.target.reset();
    fetchDonors();
}

async function deleteDonor(id) {
    await fetch(`${API_BASE}/donors/${id}`, { method: "DELETE" });
    fetchDonors();
}

// ========== BLOOD DRIVES ==========
async function fetchDrives() {
    const res = await fetch(`${API_BASE}/drives`);
    const drives = await res.json();
    const tbody = document.getElementById("driveTableBody");
    tbody.innerHTML = "";
    drives.forEach(d => {
        tbody.innerHTML += `
        <tr>
            <td>${d.id}</td>
            <td>${d.location}</td>
            <td>${d.date}</td>
            <td><button class="btn btn-danger" onclick="deleteDrive(${d.id})">Delete</button></td>
        </tr>`;
    });
}

async function addDrive(event) {
    event.preventDefault();
    const drive = {
        location: document.getElementById("driveLocation").value,
        date: document.getElementById("driveDate").value
    };
    await fetch(`${API_BASE}/drives`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(drive)
    });
    event.target.reset();
    fetchDrives();
}

async function deleteDrive(id) {
    await fetch(`${API_BASE}/drives/${id}`, { method: "DELETE" });
    fetchDrives();
}

// ========== DONATIONS ==========
async function initDonations() {
    await populateDropdowns();
    fetchDonations();
}

async function populateDropdowns() {
    const donorRes = await fetch(`${API_BASE}/donors`);
    const donors = await donorRes.json();
    const donorSelect = document.getElementById("donorSelect");
    donorSelect.innerHTML = '<option value="">Select Donor</option>';
    donors.forEach(d => donorSelect.innerHTML += `<option value="${d.id}">${d.name} (${d.bloodType})</option>`);

    const driveRes = await fetch(`${API_BASE}/drives`);
    const drives = await driveRes.json();
    const driveSelect = document.getElementById("driveSelect");
    driveSelect.innerHTML = '<option value="">Select Blood Drive</option>';
    drives.forEach(d => driveSelect.innerHTML += `<option value="${d.id}">${d.location} (${d.date})</option>`);
}

async function fetchDonations() {
    const res = await fetch(`${API_BASE}/donations`);
    const donations = await res.json();
    const tbody = document.getElementById("donationTableBody");
    tbody.innerHTML = "";
    donations.forEach(d => {
        tbody.innerHTML += `
        <tr>
            <td>${d.id}</td>
            <td>${d.donor?.name || "Unknown"}</td>
            <td>${d.bloodDrive?.location || "Unknown"}</td>
            <td>${d.date}</td>
            <td><button class="btn btn-danger" onclick="deleteDonation(${d.id})">Delete</button></td>
        </tr>`;
    });
}

async function addDonation(event) {
    event.preventDefault();
    const donorId = document.getElementById("donorSelect").value;
    const driveId = document.getElementById("driveSelect").value;
    const date = document.getElementById("donationDate").value;

    if (!donorId || !driveId) {
        alert("Please select both a donor and a blood drive!");
        return;
    }

    await fetch(`${API_BASE}/donations`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            donor: { id: donorId },
            bloodDrive: { id: driveId },
            date: date
        })
    });

    event.target.reset();
    fetchDonations();
}

async function deleteDonation(id) {
    await fetch(`${API_BASE}/donations/${id}`, { method: "DELETE" });
    fetchDonations();
}
