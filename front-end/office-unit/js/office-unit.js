"use strict";

// ------------------- TABS -------------------
const homeDiv = document.querySelector("#home");
const homeNav = document.querySelector("#home-nav");
const newSectorDiv = document.querySelector("#new-sector");
const newSectorNav = document.querySelector("#new-sector-nav");
const newEmployeeDiv = document.querySelector("#new-employee");
const newEmployeeNav = document.querySelector("#new-employee-nav");
const sectorReportsDiv = document.querySelector("#sector-reports");
const sectorReportsNav = document.querySelector("#sector-reports-nav");

let previousItem = homeDiv; // homeDiv starts visible
let previousNav = homeNav; // homeNav starts active

function changeItem(newItem, newNav) {
	if (newItem === previousItem) return;

	previousNav.classList.remove("active");
	previousItem.classList.remove("my-show");
	previousItem.classList.remove("flex-column-1");
	previousItem.classList.add("my-absolute");

	previousItem = newItem;
	previousNav = newNav;
	previousNav.classList.add("active");
	previousItem.classList.add("flex-column-1");
	previousItem.classList.remove("my-absolute");
	previousItem.classList.add("my-show");
}

homeNav.addEventListener("click", () => {
	changeItem(homeDiv, homeNav);
});

newSectorNav.addEventListener("click", () => {
	changeItem(newSectorDiv, newSectorNav);
});

newEmployeeNav.addEventListener("click", () => {
	changeItem(newEmployeeDiv, newEmployeeNav);
});

sectorReportsNav.addEventListener("click", () => {
	changeItem(sectorReportsDiv, sectorReportsNav);
});

// !------------------- TABS -------------------

// ------------------- TOAST -------------------
const dangerToast = new bootstrap.Toast(
	document.querySelector("#danger-toast")
);
const dangerToastBody = document.querySelector("#danger-toast .toast-body");
const successToast = new bootstrap.Toast(
	document.querySelector("#success-toast")
);
const successToastBody = document.querySelector("#success-toast .toast-body");

function showDangerToast(msg) {
	dangerToastBody.innerText = msg;
	dangerToast.show();
}

function showSuccesToast(msg) {
	successToastBody.innerText = msg;
	successToast.show();
}

// !------------------- TOAST -------------------

const newSectorForm = document.querySelector("#new-sector-form");
const newEmployeeForm = document.querySelector("#new-employee-form");

function handleForm(evt, formParam) {
	evt.preventDefault();
	successToast.hide();
	dangerToast.hide();

	formParam.classList.add("was-validated");
	if (!formParam.checkValidity()) {
		evt.stopPropagation();
		return null;
	}

	const formData = new FormData(formParam);
	const obj = {};

	formData.forEach((value, key) => {
		obj[key] = value;
	});

	return obj;
}

// ------------------- NEW SECTOR -------------------
newSectorForm.addEventListener("submit", async (evt) => {
	const newSector = handleForm(evt, newSectorForm);
	if (newSector === null) {
		return;
	}

	let response = null;
	try {
		response = await fetch("http://localhost:8080/sector/create", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(newSector),
		});
	} catch (e) {
		showDangerToast("Something bad happened. Try again later.");
		return;
	}

	if (response.status === 201) {
		showSuccesToast("Sector succesfully registered!");
		homeNav.click();
	} else {
		showDangerToast("Something bad happened. Try again later.");
	}
});
// !------------------- NEW SECTOR -------------------

// ------------------- NEW EMPLOYEE -------------------
// Sectors modal
let sectorCheck = document.getElementById("sectorCheck");
// Used for preventing the checkbox to be already checked in case of page reload.
document.addEventListener("DOMContentLoaded", () => {
	sectorCheck = document.getElementById("sectorCheck"); // YES, it's necessary to redo this.
	sectorCheck.checked = false; // Ensure the checkbox is always unchecked on page load

	sectorCheck.addEventListener("click", (event) => {
		event.preventDefault(); // Prevent the checkbox from being checked on click
	});
});

const sectorModalBody = document.querySelector("#sector-modal-inner-body");
const sectorFilterModalBody = document.querySelector(
	"#sectorFilterModalInnerBody"
);
const sectorCheckStringModel = `
<div class="col-12">
	<div class="form-check">
		<input class="form-check-input" type="radio" name="sectorId"
			value="__id__" id="s__id__">
		<label class="form-check-label" for="s__id__" style="color: initial;">
			__name__
		</label>
	</div>
</div>
`;
const sectorFilterCheckStringModel = `
<div class="col-12">
	<div class="form-check">
		<input class="form-check-input" type="radio" name="sectorId"
			value="__id__" id="fs__id__">
		<label class="form-check-label" for="fs__id__" style="color: initial;">
			__name__
		</label>
	</div>
</div>
`;

let sectorModalFooterButton = document.querySelector(
	"#sectorModalFooterButton"
);
let sectorModal = new bootstrap.Modal(document.getElementById("sectorModal"));
let sectorModalCheckInputs = null;

// IIFE
(async function fetchSectors() {
	let response = null;
	let sectors = null;
	try {
		response = await fetch("http://localhost:8080/sector/all", {
			method: "GET",
			headers: {
				Accept: "application/json;charset=utf-8",
			},
		});
		sectors = await response.json();
	} catch (e) {
		console.error(e);
		sectors = [
			{ name: "Printed Circuit Board (PCB) assembly", id: 1 },
			{ name: "Component Soldering", id: 2 },
			{ name: "Circuit Testing", id: 3 },
			{ name: "Casing", id: 4 },
			{ name: "Battery", id: 5 },
			{ name: "Screen and Display", id: 6 },
			{ name: "Software", id: 7 },
			{ name: "Quality Control", id: 8 },
		];
	}

	console.log(sectors);

	sectors.forEach((sector) => {
		const div1 = sectorCheckStringModel
			.replace(/__id__/g, sector.id)
			.replace(/__name__/g, sector.name);
		sectorModalBody.innerHTML += div1;

		const div2 = sectorFilterCheckStringModel
			.replace(/__id__/g, sector.id)
			.replace(/__name__/g, sector.name);
		sectorFilterModalBody.innerHTML += div2;
	});

	sectorModalCheckInputs = [
		...document.querySelectorAll("#sectorModalBody .form-check-input"),
	];
})();

sectorModalFooterButton.addEventListener("click", (e) => {
	if (sectorModalCheckInputs.some((i) => i.checked)) {
		sectorCheck.checked = true;
	} else {
		sectorCheck.checked = false;
	}
});

newEmployeeForm.addEventListener("submit", async (evt) => {
	const newEmployee = handleForm(evt, newEmployeeForm);
	if (newEmployee === null) {
		return;
	}

	// Old code for when the model was outside of the form. The logic is wrong, by the way.
	// It sould be inside handleForm(...)(?)
	// newEmployee["sectorId"] = document.querySelector(
	// 	"#sectorModal input.form-check-input:checked"
	// ).value;
	console.log(newEmployee);

	let response = null;
	try {
		response = await fetch("http://localhost:8080/employee/create", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(newEmployee),
		});
	} catch (e) {
		showDangerToast("Something bad happened. Try again later.");
		return;
	}

	if (response.status === 201) {
		showSuccesToast("Employee succesfully registered!");
		homeNav.click();
	} else {
		showDangerToast("Something bad happened. Try again later.");
	}
});
// !------------------- NEW EMPLOYEE -------------------

// ------------------- SECTOR REPORT -------------------
const cardContainer = document.querySelector(
	"#sector-reports #sector-reports-cards"
);
const sectorFilterModalFooterButton = document.querySelector(
	"#sectorFilterModalFooterButton"
);

sectorFilterModalFooterButton.addEventListener("click", async (evt) => {
	const checkedInput = document.querySelector(
		"#sectorFilterModalBody :checked"
	);
	console.log(checkedInput);
	let response = null;
	let reports = null;
	try {
		response = await fetch(
			`http://localhost:8080/sector/daily-report/${checkedInput.value}`,
			{
				method: "GET",
				headers: { Accept: "application/json" },
			}
		);
		reports = await response.json();
	} catch (e) {
		console.error(e);
		// Mock reports
		reports = [
			{
				reportCount: 1,
				sectorName: "Circuit Testing",
				problemDescription: "Personnel failure",
				reportDate: "2023-12-31",
			},
			{
				reportCount: 1,
				sectorName: "Circuit Testing",
				problemDescription: "Equipment failure",
				reportDate: "2024-09-01",
			},
			{
				reportCount: 1,
				sectorName: "Circuit Testing",
				problemDescription: "Accident",
				reportDate: "2024-09-03",
			},
			{
				reportCount: 1,
				sectorName: "Circuit Testing",
				problemDescription: "Lack of supplies",
				reportDate: "2024-09-04",
			},
			{
				reportCount: 1,
				sectorName: "Circuit Testing",
				problemDescription: "Lack of supplies",
				reportDate: "2024-09-04",
			},
			{
				reportCount: 1,
				sectorName: "Circuit Testing",
				problemDescription: "Personnel failure",
				reportDate: "2024-09-06",
			},
			{
				reportCount: 1,
				sectorName: "Circuit Testing",
				problemDescription: "Accident",
				reportDate: "2024-09-07",
			},
			{
				reportCount: 1,
				sectorName: "Circuit Testing",
				problemDescription: "Accident",
				reportDate: "2024-09-10",
			},
			{
				reportCount: 5,
				sectorName: "Circuit Testing",
				problemDescription: "Equipment failure",
				reportDate: "2024-09-11",
			},
		];
	}
	// Empty the cardContainer
	cardContainer.innerHTML = "";
	let cardAnimations = [];
	reports.forEach((report) => {
		const card = document.createElement("div");
		card.classList.add(
			"card",
			"bg-dark",
			"text-white",
			"shadow",
			"mb-3",
			"report-card",
			"card-my-grow"
		);
		card.style.width = "18rem";
		card.innerHTML = `
			<div class="card-body">
				<h5 class="card-title">Sector: ${report.sectorName}</h5>
				<p class="card-text"><strong>Problem:</strong> ${report.problemDescription}</p>
				<p class="card-text"><strong>Report Count:</strong> ${report.reportCount}</p>
				<p class="card-text"><strong>Date:</strong> ${report.reportDate}</p>
			</div>
		`;
		cardContainer.appendChild(card);
		cardAnimations.push(card);
	});
	setTimeout(() => {
		cardAnimations.forEach((c) => {
			c.classList.remove("card-my-grow");
		});
	}, 600);
});
// !------------------- SECTOR REPORT -------------------
