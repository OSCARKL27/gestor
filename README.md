GESTOR DE TAREAS 
<br>
<br>
Es un gestor de tareas que puede agregar, borrar, editar y marcar como completada una tarea, en este viene la fecha de creacion, un titulo para la tarea, una breve descripcion de lo que es
<br>
<br>
Bajar codigo a android studio y darle en run y solo se ejecutara
<br>
<br>
Estructura de base de datos
<br>
<br>
CREATE TABLE IF NOT EXISTS `tasks` (<br>
    `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,<br>
    `task_title` TEXT,<br>
    `task_description` TEXT,<br>
    `created_at` TEXT,<br>
    `is_completed` INTEGER NOT NULL<br>
);
<br>
<img width="387" height="835" alt="image" src="https://github.com/user-attachments/assets/b425fe7d-6ca1-4185-b5f3-dc7be3208598" />
<img width="387" height="851" alt="image" src="https://github.com/user-attachments/assets/8499ec54-87a8-4769-90dc-3faca13f0c49" />
<img width="389" height="673" alt="image" src="https://github.com/user-attachments/assets/48b19db1-ffd0-44d1-bf13-f7fd34c340a7" />


