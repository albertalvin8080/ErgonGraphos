"use strict";

// ------------------- TABS -------------------

const homeDiv = document.querySelector("#home");
const homeNav = document.querySelector("#home-nav");
const newSectorDiv = document.querySelector("#new-sector");
const newSectorNav = document.querySelector("#new-sector-nav");
const newEmployeeDiv = document.querySelector("#new-employee");
const newEmployeeNav = document.querySelector("#new-employee-nav");

let previousItem = homeDiv; // homeDiv starts visible
let previousNav = homeNav;

homeNav.addEventListener("click", () => {
	previousItem.classList.remove("my-show");
	previousNav.classList.remove("active");

	previousItem = homeDiv;
	previousNav = homeNav;
	previousItem.classList.add("my-show");
	previousNav.classList.add("active");
});

newSectorNav.addEventListener("click", () => {
	previousItem.classList.remove("my-show");
	previousNav.classList.remove("active");

	previousItem = newSectorDiv;
	previousNav = newSectorNav;
	previousItem.classList.add("my-show");
	previousNav.classList.add("active");
});

newEmployeeNav.addEventListener("click", () => {
	previousItem.classList.remove("my-show");
	previousNav.classList.remove("active");

	previousItem = newEmployeeDiv;
	previousNav = newEmployeeNav;
	previousItem.classList.add("my-show");
	previousNav.classList.add("active");
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

function showDangerToast() {
	dangerToastBody.innerText = "Something bad happened. Try again later.";
	dangerToast.show();
}

function showSuccesToast() {
	successToastBody.innerText = "Sector succesfully registered!";
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
		showDangerToast();
		return;
	}

	if (response.status === 201) {
		showSuccesToast();
		homeNav.click();
	} else {
		showDangerToast();
	}
});
// !------------------- NEW SECTOR -------------------

// ------------------- NEW EMPLOYEE -------------------
newEmployeeForm.addEventListener("submit", async (evt) => {
	const newEmployee = handleForm(evt, newEmployeeForm);
	if (newEmployee === null) {
		return;
	}

	let response = null;
	try {
		response = await fetch("http://localhost:8080/employee/create", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify(newEmployee),
		});
	} catch (e) {
		showDangerToast();
		return;
	}

	if (response.status === 201) {
		showSuccesToast();
		homeNav.click();
	} else {
		showDangerToast();
	}
});

// Sectors modal
const sectorCheck = document.getElementById("sectorCheck");
sectorCheck.addEventListener("click", (event) => {
	event.preventDefault();
});

const sectorModalBody = document.querySelector("#sector-modal-inner-body");
const sectorCheckStringModel = `
<div class="col-12">
	<div class="form-check">
		<input class="form-check-input" type="radio" name="sector"
			value="s__id__" id="s__id__">
		<label class="form-check-label" for="s__id__" style="color: initial;">
			__name__
		</label>
	</div>
</div>
`;

(async function fetchSectors() {
	const response = await fetch("http://localhost:8080/sector/all", {
		method: "GET",
		headers: {
			Accept: "application/json;charset=utf-8",
		},
	});
	const sectors = await response.json();
	console.log(sectors);

	sectors.forEach((sector) => {
		const div = sectorCheckStringModel
			.replace(/__id__/g, sector.id)
			.replace(/__name__/g, sector.name);
		sectorModalBody.innerHTML += div;
	});

	const sectorModalFooterButton = document.querySelector(
		"#sectorModalFooterButton"
	);
	const sectorModalCheckInputs = [
		...document.querySelectorAll("#sectorModalBody .form-check-input"),
	];
	const sectorModal = new bootstrap.Modal(
		document.getElementById("sectorModal")
	);

	sectorModalFooterButton.addEventListener("click", (e) => {
		if (sectorModalCheckInputs.some((i) => i.checked)) {
			sectorCheck.checked = true;
		} else {
			sectorCheck.checked = false;
		}
	});
})();

// !------------------- NEW EMPLOYEE -------------------
