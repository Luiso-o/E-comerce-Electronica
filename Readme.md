Este proyecto es una 🌐 tienda de electrónica en línea, diseñada para ofrecer una amplia gama de productos tecnológicos, desde computadoras y teléfonos hasta accesorios para el hogar inteligente. Utiliza 🍃 Thymeleaf para una experiencia de usuario dinámica y atractiva en el lado del cliente, y 📦 MongoDB como su sistema de base de datos para una persistencia de datos eficiente y escalable.

Características destacadas:

🛒 Catálogo de Productos: Explora una variedad de categorías como computadoras, teléfonos, impresoras, cámaras, dispositivos inteligentes para el hogar, gaming, wearables, y más. Cada producto se muestra con detalles como nombre, marca, categoría, precio, y una imagen.

🖥️ Interfaz de Usuario Dinámica: Gracias a Thymeleaf, la interfaz es interactiva, permitiendo a los usuarios una navegación fluida y una gestión eficaz de los productos, incluyendo la creación, edición, y eliminación de productos en la tienda.

💾 Persistencia con MongoDB: Los datos de los productos se almacenan de manera eficiente en MongoDB, asegurando una recuperación rápida de la información y una escalabilidad óptima para el crecimiento del inventario de la tienda.

🏠 Accesorios para el Hogar: Además de la electrónica de consumo, la tienda ofrece una gama de accesorios inteligentes para el hogar, permitiendo a los usuarios encontrar todo lo necesario para una vida conectada y moderna.

🎨 Estilo Personalizado: La aplicación utiliza Bootstrap con personalizaciones específicas para garantizar una presentación visual atractiva y una experiencia de usuario coherente a través de la tienda.

Este proyecto representa una solución completa para la venta en línea de productos electrónicos y accesorios para el hogar, destacando por su enfoque en la usabilidad, la estética, y la funcionalidad avanzada de gestión de productos.

https://github.com/Luiso-o/E-comerce-Electronica/assets/128043647/5884dd68-1886-49eb-9bb6-118090ad05bf

Prerrequisitos
Antes de empezar, asegúrate de tener instalado lo siguiente en tu sistema:

Docker Compose
Estas herramientas son esenciales para construir y ejecutar los contenedores necesarios para el proyecto.

Configuración inicial
Paso 1: Clonar el Repositorio
Para obtener una copia del proyecto en tu máquina local, clona el repositorio usando el siguiente comando:
- git clone [URL_DEL_REPOSITORIO]
- cd [NOMBRE_DEL_DIRECTORIO_DEL_PROYECTO]

Paso 2: Preparar los Datos Iniciales
El proyecto utiliza archivos JSON para inicializar la base de datos con datos de prueba. Asegúrate de que los archivos pcstore_db.Users.json y pcstore_db.Products.json estén ubicados en el directorio data-init.

Paso 3: Construir y Levantar los Servicios con Docker Compose
Para construir las imágenes de Docker y levantar los contenedores definidos en el archivo docker-compose.yml, ejecuta el siguiente comando en la terminal:
- docker-compose up --build

Este comando construirá la imagen de tu aplicación y la de MongoDB, y luego iniciará los contenedores. Si la base de datos necesita ser inicializada con datos de prueba, asegúrate de que el servicio de MongoDB esté configurado para ejecutar scripts de inicialización al arrancar.

Paso 4: Acceder a la Aplicación
Una vez que los contenedores estén corriendo, la aplicación estará accesible desde un navegador web en la dirección http://localhost:8081, asumiendo que configuraste el puerto 8081 para el servicio de la aplicación en tu docker-compose.yml.

Uso
Detalla aquí cualquier paso específico o comandos necesarios para interactuar con la aplicación, como registrarse, iniciar sesión, o agregar nuevos productos.

Detener y Limpiar
Para detener los contenedores y remover los servicios creados, puedes utilizar el siguiente comando:
- docker-compose down -v

Este comando removerá los contenedores, redes y volúmenes definidos en el docker-compose.yml.
