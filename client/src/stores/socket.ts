import { ref, computed } from 'vue'
import { defineStore } from "pinia"

export const useSocket = defineStore('socket', () => {
  const url = ref("ws://s1.ripple.com");
  const socket = ref(new WebSocket(url.value));

  function send(msg) { socket.value.send(JSON.stringify(msg)) };
  function close(msg) { socket.value.close() };

  return { send, close, socket }
})