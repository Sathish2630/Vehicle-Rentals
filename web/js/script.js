// toggle the dropdown visibility
function toggleDropdown() {
    const dropdownMenu = document.getElementById('dropdownMenu');
    dropdownMenu.style.display = dropdownMenu.style.display === 'block' ? 'none' : 'block';
}

// Close the dropdown if clicked outside
window.onclick = function(event) {
    if (!event.target.matches('.profile-pic')) {
        const dropdownMenu = document.getElementById('dropdownMenu');
        if (dropdownMenu.style.display === 'block') {
            dropdownMenu.style.display = 'none';
        }
    }
};

// Actions for menu options
function goToProfile() {
    
    console.log("Navigating to Profile");
    window.location.href = "profile.html";
}

function goToBookings() {
    
    console.log("Navigating to Bookings");
    window.location.href = "booking.html";
}

function logout() {
  
    console.log("Logging out");
    window.location.href = "logout";
}
