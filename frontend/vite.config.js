import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import fs from 'fs';
import path from 'path';
import dotenv from 'dotenv';

dotenv.config()

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    host: true,
    port: 5173,
    https: {
      key: fs.readFileSync(path.resolve(process.env.KEY_PATH, 'key.pem')),
      cert: fs.readFileSync(path.resolve(process.env.CERT_PATH, 'cert.pem')),
    },
  },
});
