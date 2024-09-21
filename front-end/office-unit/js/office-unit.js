"use strict";

const forms = document.querySelectorAll(".needs-validation");

// Loops over ALL forms
Array.from(forms).forEach((form) => {
	form.addEventListener(
		"submit",
		(event) => {
			if (!form.checkValidity()) {
				event.preventDefault();
				event.stopPropagation();
			}
			form.classList.add("was-validated");
		},
		false
	);
});

// ------------------- NEW SECTOR -------------------
const newSectorForm = document.querySelector("#new-sector-form")
const newSectorBtn = document.querySelector("#new-sector-btn");
newSectorBtn.addEventListener("click", (evt) => {
	evt.preventDefault();

	const formData = new FormData(newSectorForm);
	const newSector = {};

	formData.forEach((value, key) => {
		newSector[key] = value;
	});

    console.log(newSector);
});
// !------------------- NEW SECTOR -------------------
