# 1. Use an image that has Node.js installed
FROM node:20-alpine

# 2. Create a folder inside Docker called /app
WORKDIR /app

# 3. Copy package.json and install dependencies
COPY package*.json ./
RUN npm install

# 4. Copy everything else (your code)
COPY . .

# 5. Start the React app (Vite)
# The --host part is vital for Docker to let you see the site
CMD ["npm", "run", "dev", "--", "--host"]