# Documentación de Estilos CSS del Proyecto

## 📋 Resumen

Este proyecto ahora cuenta con un sistema de estilos CSS consistente y moderno que garantiza una experiencia visual uniforme en todas las vistas.

## 🎨 Archivos CSS Disponibles

### 1. **style.css** - Estilos Base Globales
**Ubicación:** `/src/main/resources/assets/style.css`  
**Uso:** Vistas de login, registro, eliminación, confirmaciones, etc.

**Características:**
- ✅ Diseño centrado con gradiente morado/azul de fondo
- ✅ Contenedores con sombras y bordes redondeados
- ✅ Formularios con campos estilizados y focus states
- ✅ Botones con efectos hover y gradientes
- ✅ Alertas y mensajes flash (success, error, warning, info)
- ✅ Tablas responsivas
- ✅ Sistema de espaciado con clases utilitarias

**Clases principales:**
```css
.container          /* Contenedor principal centrado */
.btn                /* Botón primario con gradiente */
.btn-success        /* Botón verde para acciones positivas */
.btn-danger         /* Botón rojo para acciones destructivas */
.btn-secondary      /* Botón gris para acciones secundarias */
.alert              /* Base para alertas */
.alert-success      /* Alerta verde de éxito */
.alert-error        /* Alerta roja de error */
.alert-warning      /* Alerta amarilla de advertencia */
.alert-info         /* Alerta azul informativa */
.hecho-card         /* Tarjeta para mostrar hechos */
.mt-1, .mt-2, .mt-3 /* Margin top (10px, 20px, 30px) */
.mb-1, .mb-2, .mb-3 /* Margin bottom (10px, 20px, 30px) */
.text-center        /* Texto centrado */
```

---

### 2. **main.css** - Estilos para Búsqueda y Home
**Ubicación:** `/src/main/resources/assets/main.css`  
**Uso:** Vista de home, búsqueda de hechos

**Características:**
- ✅ Fondo claro (#f5f7fa)
- ✅ Formularios de búsqueda con grid responsivo
- ✅ Tarjetas de resultados con hover effects
- ✅ Botones de búsqueda y limpieza
- ✅ Sección de usuario con badges

**Clases principales:**
```css
.container          /* Contenedor principal con fondo blanco */
.page-title         /* Título principal de la página */
.page-message       /* Mensaje descriptivo */
.user-section       /* Sección de información del usuario */
.user-registered    /* Badge de usuario registrado (verde) */
.user-anonymous     /* Badge de usuario anónimo (gris) */
.search-form        /* Formulario de búsqueda */
.form-grid          /* Grid responsivo para campos */
.form-group         /* Grupo de formulario (label + input) */
.btn-search         /* Botón de búsqueda (verde) */
.btn-clear          /* Botón de limpiar (gris) */
.hecho-card         /* Tarjeta para mostrar hechos */
.result-item        /* Item de resultado de búsqueda */
.result-meta        /* Metadatos del resultado */
.btn-delete         /* Botón de eliminar (rojo) */
```

---

### 3. **creacion.css** - Estilos para Formularios de Creación
**Ubicación:** `/src/main/resources/assets/creacion.css`  
**Uso:** Vistas de creación de hechos y colecciones

**Características:**
- ✅ Formulario centrado con gradiente de fondo
- ✅ Preview de archivos multimedia
- ✅ Inputs y textareas con estilos consistentes
- ✅ Mensajes flash integrados
- ✅ Totalmente responsivo

**Clases principales:**
```css
.form-container     /* Contenedor del formulario */
.flash              /* Mensaje flash */
.flash.error        /* Mensaje de error */
.flash.success      /* Mensaje de éxito */
.preview            /* Contenedor de preview multimedia */
#preview-content    /* Contenido del preview (img/video) */
```

---

### 4. **dashboard.css** - Estilos para Panel Administrativo
**Ubicación:** `/src/main/resources/assets/dashboard.css`  
**Uso:** Todas las vistas del dashboard administrativo

**Características:**
- ✅ Layout con sidebar fijo y contenido principal
- ✅ Sidebar con gradiente y navegación
- ✅ Widgets y tarjetas de estadísticas
- ✅ Tablas con hover effects
- ✅ Sistema completo de botones
- ✅ Badges y etiquetas
- ✅ Responsive (móvil friendly)

**Clases principales:**
```css
body.dashboard-body     /* Body del dashboard */
.dashboard-container    /* Contenedor flex principal */
.sidebar                /* Menú lateral fijo */
.sidebar-header         /* Encabezado del sidebar */
.sidebar-nav            /* Navegación del sidebar */
.sidebar-footer         /* Footer del sidebar */
.main-content          /* Contenido principal */
.main-header           /* Encabezado del contenido */
.widgets-grid          /* Grid de widgets */
.widget                /* Widget individual */
.widget-dato           /* Dato numérico del widget */
.section               /* Sección de contenido */
.table-container       /* Contenedor de tabla */
.form-group            /* Grupo de formulario */
.btn                   /* Botón base */
.btn-primary           /* Botón primario (morado) */
.btn-success           /* Botón de éxito (verde) */
.btn-danger            /* Botón de peligro (rojo) */
.btn-warning           /* Botón de advertencia (amarillo) */
.btn-secondary         /* Botón secundario (gris) */
.btn-sm, .btn-lg       /* Tamaños de botón */
.alert-success         /* Alerta de éxito */
.alert-error           /* Alerta de error */
.alert-warning         /* Alerta de advertencia */
.alert-info            /* Alerta informativa */
.stats-container       /* Contenedor de estadísticas */
.stat-card             /* Tarjeta de estadística */
.list-group            /* Lista grupal */
.list-group-item       /* Item de lista */
.badge                 /* Badge/etiqueta */
.badge-primary         /* Badge primario */
.badge-success         /* Badge de éxito */
.badge-danger          /* Badge de peligro */
.badge-warning         /* Badge de advertencia */
.badge-info            /* Badge informativo */
.d-flex                /* Display flex */
.justify-between       /* Justify content space-between */
.align-center          /* Align items center */
.gap-1, .gap-2, .gap-3 /* Gaps (10px, 20px, 30px) */
```

---

## 🎯 Paleta de Colores Consistente

### Colores Primarios
- **Primario:** `#667eea` → `#764ba2` (Gradiente morado/azul)
- **Éxito:** `#48bb78` → `#38a169` (Gradiente verde)
- **Peligro:** `#f56565` → `#c53030` (Gradiente rojo)
- **Advertencia:** `#ecc94b` → `#d69e2e` (Gradiente amarillo)
- **Info:** `#4299e1` (Azul claro)
- **Secundario:** `#6c757d` (Gris)

### Colores de Fondo
- **Fondo principal:** `#f5f7fa` (Gris muy claro)
- **Blanco:** `#ffffff`
- **Fondo alternativo:** `#f8f9fa`

### Colores de Texto
- **Texto principal:** `#333`
- **Texto secundario:** `#666`
- **Texto claro:** `#888`
- **Texto oscuro:** `#555`

### Colores de Bordes
- **Borde principal:** `#e0e0e0`
- **Borde claro:** `#f0f0f0`

---

## 📱 Responsive Design

Todos los archivos CSS incluyen breakpoints responsivos:

- **Desktop:** > 1024px (diseño completo)
- **Tablet:** 768px - 1024px (ajustes medios)
- **Mobile:** < 768px (layout vertical, sidebar completo)
- **Small Mobile:** < 480px (optimización para pantallas pequeñas)

---

## ✨ Efectos y Animaciones

### Animaciones incluidas:
- ✅ `fadeIn` - Aparición suave de contenedores
- ✅ `slideIn` - Deslizamiento de alertas
- ✅ Hover effects en botones (translateY + box-shadow)
- ✅ Transitions suaves en enlaces y formularios
- ✅ Focus states con sombras de color

---

## 🔧 Uso Recomendado por Vista

| Vista | CSS a usar | Clase body |
|-------|-----------|-----------|
| Login/Registro | `style.css` | (ninguna) |
| Home | `main.css` | (ninguna) |
| Búsqueda de Hechos | `main.css` | (ninguna) |
| Crear Hecho/Colección | `creacion.css` | (ninguna) |
| Dashboard Admin | `dashboard.css` | `dashboard-body` |
| Estadísticas | `dashboard.css` | `dashboard-body` |
| Gestión de Solicitudes | `dashboard.css` | `dashboard-body` |
| Eliminación | `style.css` | (ninguna) |
| Confirmaciones | `style.css` | (ninguna) |

---

## 📝 Ejemplos de Uso

### Botón de Acción Principal
```html
<button type="submit" class="btn">Enviar</button>
```

### Botón de Éxito
```html
<a href="/crear" class="btn btn-success">Crear Nuevo</a>
```

### Alerta de Error
```html
<div class="alert alert-error">
  Ha ocurrido un error al procesar la solicitud
</div>
```

### Tarjeta de Hecho
```html
<div class="hecho-card">
  <h3>Título del Hecho</h3>
  <p>Descripción del hecho...</p>
</div>
```

### Formulario con Grid
```html
<form class="search-form">
  <div class="form-grid">
    <div class="form-group">
      <label>Campo 1</label>
      <input type="text" name="campo1">
    </div>
    <div class="form-group">
      <label>Campo 2</label>
      <input type="text" name="campo2">
    </div>
  </div>
  <button type="submit" class="btn-search">Buscar</button>
</form>
```

---

## ✅ Mejoras Implementadas

1. ✅ **Creado `style.css`** - Archivo base que faltaba con estilos globales completos
2. ✅ **Actualizado `main.css`** - Estilos modernos para búsqueda y home
3. ✅ **Actualizado `creacion.css`** - Estilos completos para formularios de creación
4. ✅ **Actualizado `dashboard.css`** - Sistema completo para el dashboard administrativo
5. ✅ **Consistencia visual** - Paleta de colores unificada
6. ✅ **Efectos modernos** - Hover, focus, animations
7. ✅ **Responsive design** - Funciona en todos los dispositivos
8. ✅ **Actualizado vistas HTML** - Eliminado estilos inline, usando clases CSS

---

## 🚀 Próximos Pasos Recomendados

- [ ] Revisar todas las vistas restantes para eliminar estilos inline
- [ ] Agregar iconos con Font Awesome o similar
- [ ] Implementar modo oscuro (opcional)
- [ ] Agregar más animaciones de transición entre páginas

---

**Fecha de actualización:** 2025-01-07  
**Estado:** ✅ Completado

