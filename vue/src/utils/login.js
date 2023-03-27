const container = document.getElementById('container');
const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');

signUpButton.onclick = function() {
    container.classList.add('penal-right-active');
}

signInButton.onclick = function() {
    container.classList.remove('penal-right-active');
}

