from django.contrib import admin
from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views.hotel_viewset import HotelViewSet
from .views.room_viewset import RoomViewSet
from .views.customer_viewset import CustomerViewSet
from .views.booking_viewset import BookingViewSet


from drf_spectacular.views import SpectacularAPIView, SpectacularRedocView, SpectacularSwaggerView

router = DefaultRouter()
router.register(r'hotel', HotelViewSet)
router.register(r'room', RoomViewSet)
router.register(r'customer', CustomerViewSet)
router.register(r'booking', BookingViewSet)

interaction_patterns = [
]

urlpatterns = [
    path('admin/', admin.site.urls),
    path('api/', include(router.urls)),
        path('', include((interaction_patterns, 'interactions'), namespace='interactions')),
    path('api/schema/', SpectacularAPIView.as_view(), name='schema'),
    path('swagger/', SpectacularSwaggerView.as_view(url_name='schema'), name='swagger-ui'),
    path('redoc/', SpectacularRedocView.as_view(url_name='schema'), name='redoc'),
]

