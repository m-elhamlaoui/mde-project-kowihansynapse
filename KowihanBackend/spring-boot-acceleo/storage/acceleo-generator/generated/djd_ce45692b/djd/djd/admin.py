from django.contrib import admin
from .models.hotel import Hotel
from .models.room import Room
from .models.customer import Customer
from .models.booking import Booking

@admin.register(Hotel)
class HotelAdmin(admin.ModelAdmin):
    list_display = ['id', 'name', 'address']
    search_fields = ['name', 'address']

@admin.register(Room)
class RoomAdmin(admin.ModelAdmin):
    list_display = ['id']
    list_filter = ['isavailable']

@admin.register(Customer)
class CustomerAdmin(admin.ModelAdmin):
    list_display = ['id', 'firstname', 'lastname']
    search_fields = ['firstname', 'lastname']

@admin.register(Booking)
class BookingAdmin(admin.ModelAdmin):
    list_display = ['id']

