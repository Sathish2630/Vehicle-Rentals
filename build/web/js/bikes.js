// Array of bike data 
const bikes = [
    { name: "Suzuki Intruder (Cruiser)", price: 3500, kmIncluded: 670, excessRate: 3.5, availability: 1, imageUrl: "img/SuzukiIntruderCruiser.png" },
    { name: "Bajaj Avenger 220 Cruise", price: 2700, kmIncluded: 670, excessRate: 5, availability: 5, imageUrl: "img/BajajAvengerCruise220.png" },
    { name: "Bajaj Avenger 220 Street", price: 2220, kmIncluded: 670, excessRate: 5, availability: 5, imageUrl: "img/BajajAvengerStreet220.png" },
    { name: "Bajaj Dominar 400 ABS", price: 1920, kmIncluded: 670, excessRate: 5, availability: 5, imageUrl: "img/BajajDominar400ABS.png" },
    { name: "Bajaj Pulsar 150", price: 1200, kmIncluded: 670, excessRate: 5, availability: 5, imageUrl: "img/BajajPulsar150.png" },
    { name: "Honda Hornet", price: 1000, kmIncluded: 670, excessRate: 5, availability: 5, imageUrl: "img/HondaHornet.png" },
    { name: "HondaHornet2.0", price: 1500, kmIncluded: 670, excessRate: 5, availability: 5, imageUrl: "img/HondaHornet2.0.jpg" },
    { name: "Honda Shine 125 SP ", price: 2000, kmIncluded: 670, excessRate: 5, availability: 5, imageUrl: "img/HondaShineSP125.png" },
    { name: "Royal Enfield 350 Classic", price: 3000, kmIncluded: 670, excessRate: 5, availability: 5, imageUrl: "img/RoyalEnfieldClassic350.png" },
    { name: "Royal Enfield 411cc Himalayan", price: 4500, kmIncluded: 670, excessRate: 5, availability: 5, imageUrl: "img/RoyalEnfieldHimalayan411cc.png" },
    { name: "Royal Enfield 350 Thunderbird", price: 3600, kmIncluded: 670, excessRate: 5, availability: 5, imageUrl: "img/RoyalEnfieldThunderbird350.png" },
    { name: "TVS Apache 160 RTR", price: 1500, kmIncluded: 670, excessRate: 5, availability: 5, imageUrl: "img/TVSApacheRTR160.png" },

 
];

// display bikes on the page
function displayBikes() {
    const bikeList = document.getElementById('bike-list');
    bikeList.innerHTML = ''; 

    bikes.forEach(bike => {
        const bikeCard = document.createElement('div');
        bikeCard.className = 'bike-card';
        
        bikeCard.innerHTML = `
            <img src="${bike.imageUrl}" alt="${bike.name}">
            <div class="deposit">Zero Deposit</div>
            <div class="availability">${bike.availability} Bike${bike.availability > 1 ? 's' : ''} left</div>
            <h3>${bike.name}</h3>
            <div class="price">₹ ${bike.price}</div>
            <p>(${bike.kmIncluded} KM included)</p>
            <p>Excess ₹ ${bike.excessRate}/km</p>
            <button class="book-btn" onclick="openBookingModal('${bike.name}')">BOOK NOW</button>
        `;

        bikeList.appendChild(bikeCard);
    });
}

// Modal functionality
const modal = document.getElementById("bookingModal");
const closeBtn = document.getElementsByClassName("close")[0];

// Open booking modal
function openBookingModal(bikeName) {
    modal.style.display = "block";
    document.getElementById('bookingDate').dataset.bike = bikeName;
}


closeBtn.onclick = function() {
    modal.style.display = "none";
}

// Close modal if clicked outside
window.onclick = function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
}


// Initialize the bike display
displayBikes();
