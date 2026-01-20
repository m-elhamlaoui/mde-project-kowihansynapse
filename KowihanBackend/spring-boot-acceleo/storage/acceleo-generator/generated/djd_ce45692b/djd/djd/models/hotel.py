from django.db import models
import uuid

class Hotel(models.Model):
    id = models.AutoField(primary_key=True)
    name = models.CharField(max_length=255, null=True, blank=True)
    address = models.CharField(max_length=255, null=True, blank=True)
    stars = models.IntegerField(null=True, blank=True)

    class Meta:
        db_table = 'hotels'

    def __str__(self):
        return f"Hotel {self.id}"
