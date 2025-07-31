# Use Node 20 (or 22) to meet Angular CLI requirements
FROM node:20 AS build
WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build --prod

# Serve with Nginx
FROM nginx:alpine
COPY --from=build /app/dist/kata-ui/browser /usr/share/nginx/html
EXPOSE 80
