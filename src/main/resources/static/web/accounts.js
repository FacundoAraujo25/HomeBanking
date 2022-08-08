document.addEventListener("DOMContentLoaded", function(event) {

    const showNavbar = (toggleId, navId, bodyId, headerId) =>{
    const toggle = document.getElementById(toggleId),
    nav = document.getElementById(navId),
    bodypd = document.getElementById(bodyId),
    headerpd = document.getElementById(headerId)
    
    // Validate that all variables exist
    if(toggle && nav && bodypd && headerpd){
    toggle.addEventListener('click', ()=>{
    // show navbar
    nav.classList.toggle('show')
    // change icon
    toggle.classList.toggle('bx-x')
    // add padding to body
    bodypd.classList.toggle('body-pd')
    // add padding to header
    headerpd.classList.toggle('body-pd')
    })
    }
    }
    
    showNavbar('header-toggle','nav-bar','body-pd','header')
    
    /*===== LINK ACTIVE =====*/
    const linkColor = document.querySelectorAll('.nav_link')
    
    function colorLink(){
    if(linkColor){
    linkColor.forEach(l=> l.classList.remove('active'))
    this.classList.add('active')
    }
    }
    linkColor.forEach(l=> l.addEventListener('click', colorLink))
    
    // Your code to run since DOM is loaded and ready
    });

const app = Vue.createApp({

    data() {
        return {
            clientes:[],
            idCliente:0,
            cliente1:[],
            accounts:[],
            idAccount:0,
            cuenta1:[],
            loans:[],
            cards:[],
            debitCards:[],
            creditCards:[],
            fechaTarjeta:"",
            mesTarjeta:"",
            anioTarjeta:"",
            accountType:"",
        }
    },

    created() {
        axios.get('/api/clients')
            .then(datos => {
                this.clientes = datos.data
            })

        axios.get('http://localhost:8585/api/clients/current')
            .then(datos =>{
                this.cliente1 = datos.data
                console.log(this.cliente1)
                this.cards = this.cliente1.cards
                this.cards.forEach(card => card.cardType == 'DEBIT' && !card.disable ? this.debitCards.push(card) : card.cardType == 'CREDIT' && !card.disable ? this.creditCards.push(card):"");
                this.accounts = datos.data.accounts.sort((a1,a2) => a1.id - a2.id);
                this.accounts = this.accounts.filter(account => account.disable == false);
                this.cuenta1 = this.accounts[0];
                this.loans = this.cliente1.clientLoans
                console.log(this.accounts);
            })

        

    },

    methods: {
        
        disableAccount(id){

            axios.patch('/api/clients/current/accounts',
            `id=${id}`,
            { headers: { "content-type": "application/x-www-form-urlencoded" } }
            )
                .then(function (response) {
                    console.log("Account deleted!!!");

                    window.location.href = "/web/accounts.html";
                })
                        .catch(function (error) {
                        console.log(error);
                        });

        },
            
        thruDateTarjeta(fecha){
                    
                    const thruDate = new Date (fecha)

                    this.mesTarjeta = thruDate.getMonth()+1
                    if(this.mesTarjeta <= 9)
                    {
                        this.mesTarjeta = "0" + this.mesTarjeta
                    }

                    this.anioTarjeta = thruDate.getFullYear()
                    this.anioTarjeta = this.anioTarjeta.toString().slice(2)

                    this.fechaTarjeta = this.mesTarjeta + "/"  + this.anioTarjeta
                    return this.fechaTarjeta

                },

        numTarjeta(numero){
            let numeroTarjeta = numero.padStart(4,"-")
            return numeroTarjeta
        },
                
                // thruDateTarjeta(fecha){
                // create a formatter
                //const formatter = DateTimeFormatter.ofPattern("MMM / uu");

                // create a LocalDate object and
                // this.fechaTarjeta = fecha.getMonth()+1 + "/" + fecha.getYear()
                
                // console.log(this.fechaTarjeta)
                // return this.fechaTarjeta
                // },

                ultimosNumTarjeta(numero){
        
                    this.ultimos4Tarjeta = numero.slice(-4);
                    return this.ultimos4Tarjeta
                },
                
                signOutClient(){
                    
                    axios.post('/api/logout')
                        .then(response => 
                    window.location.href = "/web/index.html")
                },

                createAccount(){
                    axios.post("/api/clients/current/accounts",
                    `accountType=${this.accountType}`,
            { headers: { "content-type": "application/x-www-form-urlencoded" } }
            )
                    .then(console.log("Account created"))
                        .then(function (loadData) {
                            location.reload(loadData);
                        })
                },

                numberAccount(){

                }
        

    },
    
    computed: {


    
},
}).mount('#app')
