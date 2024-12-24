
const locationInput = document.getElementById('locationInput');
const dateInput = document.getElementById('dateInput');
const timeInput = document.getElementById('timeInput');
const selectBikeButton = document.getElementById('selectBikeButton');

// Function to check if all inputs are filled
function checkInputs() {
    const location = locationInput.value.trim();
    const date = dateInput.value.trim();
    const time = timeInput.value.trim();

    // Enable the button only if all inputs are filled
    selectBikeButton.disabled = !(location && date && time);
}


locationInput.addEventListener('input', checkInputs);
dateInput.addEventListener('input', checkInputs);
timeInput.addEventListener('change', checkInputs);


selectBikeButton.addEventListener('click', () => {
    alert(`Location: ${locationInput.value}, Date: ${dateInput.value}, Time: ${timeInput.value}`);
});
