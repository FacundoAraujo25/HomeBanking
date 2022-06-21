// const signInBtn = document.getElementById("signIn");
// const signUpBtn = document.getElementById("signUp");
// const fistForm = document.getElementById("form1");
// const secondForm = document.getElementById("form2");
// const container = document.querySelector(".container");

// signInBtn.addEventListener("click", () => {
// container.classList.remove("right-panel-active");
// });

// signUpBtn.addEventListener("click", () => {
// container.classList.add("right-panel-active");
// });

// fistForm.addEventListener("submit", (e) => e.preventDefault());
// secondForm.addEventListener("submit", (e) => e.preventDefault());


Vue.createApp({

    data() {
        return {
                clientFirstName:"",
                clientLastName:"",
                newClientMail:"",
                newClientPassword:"",
                clientMail:"",
                clientPassword:"",
        }
    },

    created() {

    
    },

    methods: {

        //axios.post('/api/login', 
        //"mail=melba@mindhub.com&password=melba",
        //{headers:{'content-type':'application/x-www-form-urlencoded'}})
        //.then(response => console.log('signed in!!!'))
        signInClient(){
            axios.post('/api/login',
            `mail=${this.clientMail}&password=${this.clientPassword}`,
                {headers:{'content-type':'application/x-www-form-urlencoded'}
                })
            .then(function(response){

                console.log('signed in!!!')
                
                window.location.href = "/web/accounts.html";

            })
            .catch(function(error){
                console.log(error)
            });
        },

        signUpClient(){
            axios.post('/api/clients',
            `firstName=${this.clientFirstName}&lastName=${this.clientLastName}&mail=${this.newClientMail}&password=${this.newClientPassword}`,
                {headers:{'content-type':'application/x-www-form-urlencoded'}
                })
            .then(response =>{

                console.log("Registered!!!!")

                axios.post('/api/login',
                `mail=${this.newClientMail}&password=${this.newClientPassword}`,
                    {headers:{'content-type':'application/x-www-form-urlencoded'}
                    })
                .then(function(response){
                    window.location.href = "/web/accounts.html";
                })

            })
            .catch(function(error){
                console.log(error)
            });

            // axios.post('/api/login',
            // `mail=${this.newClientMail}&password=${this.newClientPassword}`,
            //     {headers:{'content-type':'application/x-www-form-urlencoded'}
            //     })
            // .then(function(response){
            //     window.location.href = "/web/accounts.html";
            // })
            // .catch(function(error){
            //     console.log(error)
            // });
        },

        signOutClient(){
            axios.post('/api/logout')
                .then(function(response){

                console.log('signed out!!!')
                
                window.location.href = "/web/index.html";

                })
        },


},

    
    computed: {


},


}).mount('#app')
