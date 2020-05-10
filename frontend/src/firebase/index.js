import firebase from 'firebase'
import 'firebase/firestore'
import 'firebase/auth'

// firebase init goes here
const config = {
  apiKey: 'AIzaSyBJR48mjannc8dsNiHbZL-oUWgAB7zdHio',
  authDomain: 'javaproj-f10f9.firebaseapp.com',
  databaseURL: 'https://javaproj-f10f9.firebaseio.com',
  projectId: 'javaproj-f10f9',
  storageBucket: 'javaproj-f10f9.appspot.com',
  messagingSenderId: '137489575302',
  appId: '1:137489575302:web:150b7267faf54bc1b55007',
  measurementId: 'G-CGJKHJQBF8'
}
firebase.initializeApp(config)
// firebase.analytics()

// firebase utils
// const db = firebase.firestore()
const auth = firebase.auth()
const currentUser = auth.currentUser

// firebase collections
// const usersCollection = db.collection('users')
// const postsCollection = db.collection('posts')
// const commentsCollection = db.collection('comments')
// const likesCollection = db.collection('likes')

export default {
  auth,
  currentUser,
  firebase
}
