object Resources {
  type Resource = {
    def close()
  }
  def closeResource(r: Resource) {
    r.close()
  }

  closeResource(System.in)
}
