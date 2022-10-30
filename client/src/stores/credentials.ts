import { ref, computed } from 'vue'
import { defineStore } from "pinia"

export const getCredentials = defineStore('credentials', () => {
  const id = ref(localStorage.getItem("id"));

  function authorized() { 
    return (id.value != null)
  }

  function setId(nid) { 
    localStorage.setItem("id", nid);
    id.value = nid 
  }

  function logout() {
    localStorage.removeItem("id")
    id.value = null 
  }

  return { setId, authorized, logout, id }
})