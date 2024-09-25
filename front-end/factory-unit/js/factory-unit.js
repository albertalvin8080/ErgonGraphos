"use strict";

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

// -------------- SELECTORS --------------
let sectorId = null;
let employeeId = null;
let problemId = null;

const sectorSelectorRow = document.querySelector("#sector-selector .row");
const employeeSelectorRow = document.querySelector("#employee-selector .row");
const problemSelectorRow = document.querySelector("#problem-selector .row");

const sectorSelector = document.querySelector("#sector-selector");
const sectorBreadcrumb = document.querySelector("#sector-breadcrumb");
const employeeSelector = document.querySelector("#employee-selector");
const employeeBreadcrumb = document.querySelector("#employee-breadcrumb");
const problemSelector = document.querySelector("#problem-selector");
const problemBreadcrumb = document.querySelector("#problem-breadcrumb");

let previousItem = sectorSelector;
let previousBreadcrumb = sectorBreadcrumb;

function nextItem(newItem, newBreadcrumb) {
	if (newItem === previousItem) return;

	previousBreadcrumb.classList.remove("active");
	previousBreadcrumb.classList.add("passed");
	previousItem.classList.remove("my-show");
	previousItem.classList.add("my-fixed");

	previousItem = newItem;
	previousBreadcrumb = newBreadcrumb;
	previousBreadcrumb.classList.add("active");
	previousItem.classList.remove("my-fixed");
	previousItem.classList.add("my-show");
}

function sectorButtonPressed(btnTarget) {
	sectorId = btnTarget.sectorId;
	nextItem(employeeSelector, employeeBreadcrumb);
	initEmployees();
}

function employeeButtonPressed(btnTarget) {
	employeeId = btnTarget.employeeId;
	nextItem(problemSelector, problemBreadcrumb);
}

async function problemButtonPressed(btnTarget) {
	problemId = btnTarget.problemId;
	reportProblem();
	resetVariables();
	nextItem(sectorSelector, sectorBreadcrumb);
	resetStyle(); // resetStyle() must be called after nextItem() because it will add "passed" to the breadcrumb
}

async function initProblems() {
	let problems = null;
	try {
		const response = await fetch(`http://localhost:8080/problem/all`, {
			method: "GET",
			headers: { Accept: "application/json;charset=utf-8" },
		});
		problems = await response.json();
	} catch (e) {
		console.error(e);
		problems = [
			{ id: 1, description: "Mock 1" },
			{ id: 1, description: "Mock 2" },
			{ id: 1, description: "Mock 3" },
			{ id: 1, description: "Mock 4" },
			{ id: 1, description: "Mock 5" },
			{ id: 1, description: "Mock 6" },
		];
	}
	problems.forEach((p) => {
		const colDiv = document.createElement("div");
		colDiv.classList.add("col-6");

		const btn = document.createElement("button");
		btn.classList.add("btn", "btn-primary");
		btn.innerText = p.description;
		btn.problemId = p.id;

		btn.addEventListener("click", (evt) => {
			problemButtonPressed(evt.currentTarget);
		});

		colDiv.appendChild(btn);
		problemSelectorRow.appendChild(colDiv);
	});
}

async function initEmployees() {
	let employees = null;
	try {
		const response = await fetch(
			`http://localhost:8080/employee/sector/${sectorId}`,
			{
				method: "GET",
				headers: { Accept: "application/json;charset=utf-8" },
			}
		);
		employees = await response.json();
	} catch (e) {
		console.error(e);
		employees = [
			{ id: 1, name: "Mock 1" },
			{ id: 1, name: "Mock 2" },
			{ id: 1, name: "Mock 3" },
			{ id: 1, name: "Mock 4" },
			{ id: 1, name: "Mock 5" },
		];
	}
	employees.forEach((e) => {
		const colDiv = document.createElement("div");
		colDiv.classList.add("col-6");

		const btn = document.createElement("button");
		btn.classList.add("btn", "btn-primary", "p-0");

		const img = document.createElement("img");
		img.classList.add("profile-img");
		img.src = "img/blank-profile-picture.png";
		btn.appendChild(img);

		const text = document.createElement("div");
		text.classList.add("profile-name");
		text.innerText = e.name;
		btn.appendChild(text);
		btn.employeeId = e.id;

		btn.addEventListener("click", (evt) => {
			employeeButtonPressed(evt.currentTarget);
		});

		colDiv.appendChild(btn);
		employeeSelectorRow.appendChild(colDiv);
	});
}

async function initSectors() {
	let sectors = null;
	try {
		const response = await fetch("http://localhost:8080/sector/all", {
			method: "GET",
			headers: { Accept: "application/json;charset=utf-8" },
		});
		sectors = await response.json();
	} catch (e) {
		console.error(e);
		sectors = [
			{ id: 1, name: "Mock 1" },
			{ id: 1, name: "Mock 2" },
			{ id: 1, name: "Mock 3" },
			{ id: 1, name: "Mock 4" },
			{ id: 1, name: "Mock 5" },
		];
	}
	sectors.forEach((s) => {
		const colDiv = document.createElement("div");
		colDiv.classList.add("col-6");

		const btn = document.createElement("button");
		btn.classList.add("btn", "btn-primary");
		btn.innerText = s.name;
		btn.sectorId = s.id;

		btn.addEventListener("click", (evt) => {
			sectorButtonPressed(evt.currentTarget);
		});

		colDiv.appendChild(btn);
		sectorSelectorRow.appendChild(colDiv);
	});
}
// !-------------- SELECTORS --------------

async function init() {
	initSectors();
	// await initEmployees(); // May only be initialized after the sector is choosen by the user.
	initProblems();
}
init();

function resetVariables() {
	sectorId = null;
	employeeId = null;
	problemId = null;
	sectorSelectorRow.innerHTML = "";
	employeeSelectorRow.innerHTML = "";
	problemSelectorRow.innerHTML = "";
	init();
}

function resetStyle() {
	sectorBreadcrumb.classList.remove("passed");
	employeeBreadcrumb.classList.remove("passed");
	problemBreadcrumb.classList.remove("passed");
}

async function reportProblem() {
	let response = null;
	try {
		response = await fetch("http://localhost:8080/report/create", {
			method: "POST",
			headers: { "Content-Type": "application/json" },
			body: JSON.stringify({ employeeId, problemId }),
		});
	} catch (e) {
		console.error(e);
		showDangerToast("Something bad occured. Try again later.");
		return;
	}

	if (response.status === 201) {
		showSuccesToast("Thank you for your feedback!");
	} else {
		showDangerToast("Something bad occured. Try again later.");
	}
}
