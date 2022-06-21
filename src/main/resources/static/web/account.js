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

Vue.createApp({

    data() {
        return {
            account:[],
            transactions:[],
            accounts:[],
            accountNumber:'',
        
        }
    },

    created() {

        const urlParams = new URLSearchParams(window.location.search);
        const idAccount = urlParams.get('id');
        
        axios.get('/api/clients/current/accounts/' + idAccount)
            .then(datos =>{
                console.log(datos)
                this.account = datos.data
                this.accountNumber = this.account.number
                this.transactions = this.account.transactions
                this.transactions = this.transactions.sort((t1,t2) => t1.id - t2.id);
            })


        axios.get('/api/clients/current/accounts/')
            .then(datos =>{
                this.accounts = datos.data
            })

    },

    methods: {
        crearCliente(){
            axios.post('/rest/clients',{
                nombre:this.nombreCliente,
                apellido:this.apellidoCliente,
                mail:this.mailCliente,
            })
            .then(function(response){
                console.log(response)
            })
            .catch(function(error){
                console.log(error)
            });
            
        },

        borrarCliente(id){
            console.log(id)
                axios.delete(id)
                // .then(response =>{
                //     this.clientes.splice(id[id.length-1],1)
                //     })
                    .then(function (loadData) {
                        location.reload(loadData);
                    })
                },
                            
    },
    
    computed: {


    
},
}).mount('#app')
