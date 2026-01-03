from django.db import models
import uuid

class Room(models.Model):
    idroom = models.AutoField(primary_key=True)
    isavailable = models.BooleanField(default=False)
    price = models.FloatField(null=True, blank=True)
    hotel = models.OneToOneField('Hotel', on_delete=models.CASCADE)
    booking = models.ForeignKey('Booking', on_delete=models.CASCADE)

    class Meta:
        db_table = 'rooms'

    def __str__(self):
        return f"Room {self.idroom}"
