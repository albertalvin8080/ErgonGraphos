"use strict";

let sectorId = null;
let employeeId = null;
let problemId = null;

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

async function sectorButtonPressed(btnTarget) {
	sectorId = btnTarget.sectorId;
	await initEmployees();
	nextItem(employeeSelector, employeeBreadcrumb);
}

async function employeeButtonPressed(btnTarget) {
	employeeId = btnTarget.employeeId;
	nextItem(problemSelector, problemBreadcrumb);
}

async function problemButtonPressed(btnTarget) {
	problemId = btnTarget.problemId;
	// nextItem(problemSelector, problemBreadcrumb);
}

// -------------- SECTORS --------------
const sectorSelectorRow = document.querySelector("#sector-selector .row");
const employeeSelectorRow = document.querySelector("#employee-selector .row");
const problemSelectorRow = document.querySelector("#problem-selector .row");

async function initProblems() {
	const response = await fetch(`http://localhost:8080/problem/all`, {
		method: "GET",
		headers: { Accept: "application/json;charset=utf-8" },
	});
	const problems = await response.json();
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
	const response = await fetch(
		`http://localhost:8080/employee/sector/${sectorId}`,
		{
			method: "GET",
			headers: { Accept: "application/json;charset=utf-8" },
		}
	);
	const employees = await response.json();
	employees.forEach((e) => {
		const colDiv = document.createElement("div");
		colDiv.classList.add("col-6");

		const btn = document.createElement("button");
		btn.classList.add("btn", "btn-primary");
		btn.innerText = e.name;
		btn.employeeId = e.id;

		btn.addEventListener("click", (evt) => {
			employeeButtonPressed(evt.currentTarget);
		});

		colDiv.appendChild(btn);
		employeeSelectorRow.appendChild(colDiv);
	});
}

async function initSectors() {
	const response = await fetch("http://localhost:8080/sector/all", {
		method: "GET",
		headers: { Accept: "application/json;charset=utf-8" },
	});
	const sectors = await response.json();
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
// !-------------- SECTORS --------------

(async function () {
	await initSectors();
	// await initEmployees(); // May only be initialized after the sector is choosen by the user.
	await initProblems();
})();
