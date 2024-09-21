"use strict";

// ------------------- TABS -------------------

const homeDiv = document.querySelector("#home");
const homeNav = document.querySelector("#home-nav");
const newSectorDiv = document.querySelector("#new-sector");
const newSectorNav = document.querySelector("#new-sector-nav");

let previousItem = homeDiv; // homeDiv start visible
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

// !------------------- TOAST -------------------

// ------------------- NEW SECTOR -------------------
const newSectorForm = document.querySelector("#new-sector-form");
// const newSectorBtn = document.querySelector("#new-sector-btn");

newSectorForm.addEventListener("submit", async (evt) => {
	evt.preventDefault();
	successToast.hide();
	dangerToast.hide();

	newSectorForm.classList.add("was-validated");
	if (!newSectorForm.checkValidity()) {
		evt.stopPropagation();
		return;
	}

	const formData = new FormData(newSectorForm);
	const newSector = {};

	formData.forEach((value, key) => {
		newSector[key] = value;
	});

	const response = await fetch("http://localhost:8080/sector/create", {
		method: "POST",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify(newSector),
	});

	if (response.status === 201) {
		successToastBody.innerText = "Sector succesfully registered!";
		successToast.show();
		homeNav.click();
	} else {
		dangerToastBody.innerText = "Something bad happened. Try again later.";
		dangerToast.show();
	}
});
// !------------------- NEW SECTOR -------------------
